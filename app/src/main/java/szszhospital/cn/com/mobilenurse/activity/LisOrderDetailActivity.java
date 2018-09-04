package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LisTableAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLisDetailBinding;
import szszhospital.cn.com.mobilenurse.entity.LisTableViewModel;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderDetailContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LisOrderDetailPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder_Table;

public class LisOrderDetailActivity extends BasePresentActivity<ActivityLisDetailBinding, LisOrderDetailPresenter> implements LisOrderDetailContract.View {

    private static final String KEY_DATA = "data";
    private LisOrder        mLisOrder;
    private LisTableAdapter mTableViewAdapter;
    private static final String TAG = "LisOrderDetailActivity";

    public static void startLisOrderDetailActivity(Context context, LisOrder lisOrder) {
        Intent intent = new Intent(context, LisOrderDetailActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) lisOrder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mLisOrder = getIntent().getParcelableExtra(KEY_DATA);
    }

    @Override
    protected void initData() {
        super.initData();
        List<LisOrder> lisOrders = SQLite.select().from(LisOrder.class).where(LisOrder_Table.LabEpisode.eq(mLisOrder.LabEpisode)).queryList();
        if (lisOrders.size() > 0) {
            StringBuilder sb = new StringBuilder();
            StringBuilder titls = new StringBuilder();
            for (int i = 0; i < lisOrders.size(); i++) {
                String visitNumberReportDR = lisOrders.get(i).VisitNumberReportDR;
                String OrdItemName = lisOrders.get(i).OrdItemName;
                if (i != lisOrders.size() - 1) {
                    sb.append(visitNumberReportDR).append("^");
                    titls.append(OrdItemName).append("+");
                } else {
                    sb.append(visitNumberReportDR);
                    titls.append(OrdItemName);
                }
            }
            String params = sb.toString();
            Log.d(TAG, "initData: " + params);
            mPresenter.getLisOrderListDetail(params);
            mDataBinding.toolbar.setTitle(titls.toString());
        } else {
            mPresenter.getLisOrderListDetail(mLisOrder.VisitNumberReportDR);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.toolbar.setTitle(mLisOrder.OrdItemName);
        mDataBinding.toolbar.setSubtitle("申请日期:" + mLisOrder.ReqDateTime + "   报告日期:" + mLisOrder.AuthDateTime);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lis_detail;
    }

    @Override
    public void showProgress() {
        mDataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLisOrderListDetail(List<LisOrderDetail> list) {
        LisTableViewModel tableViewModel = new LisTableViewModel(list);
        mTableViewAdapter = new LisTableAdapter(getApplicationContext(), tableViewModel);
        mDataBinding.tableView.setAdapter(mTableViewAdapter);
        mTableViewAdapter.setAllItems(tableViewModel.getSimpleColumnHeaderList(), tableViewModel.getSimpleRowHeaderList(), tableViewModel.getCellList());
    }

    @Override
    public void refresh() {

    }

    @Override
    protected LisOrderDetailPresenter initPresenter() {
        return new LisOrderDetailPresenter();
    }
}
