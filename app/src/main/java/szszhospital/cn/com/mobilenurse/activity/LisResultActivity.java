package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LisResultAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLisResultBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LisResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder_Table;
import szszhospital.cn.com.mobilenurse.view.LisChartDialogFragment;

public class LisResultActivity extends BasePresentActivity<ActivityLisResultBinding, LisResultPresenter> implements LisResultContract.View {

    private static final String TAG  = "LisOrderDetailActivity";
    private static final String DATA = "data";
    private LisOrder               mLisOrder;
    private LisResultAdapter       mAdapter;
    private LisChartDialogFragment mDialogFragment;

    public static void startLisResultActivity(Context context, LisOrder lisOrder) {
        Intent intent = new Intent(context, LisResultActivity.class);
        intent.putExtra(DATA, (Parcelable) lisOrder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mLisOrder = getIntent().getParcelableExtra(DATA);
        mAdapter = new LisResultAdapter(R.layout.item_lis_result);
    }

    @Override
    protected void initView() {
        mDataBinding.toolbar.setTitle(mLisOrder.OrdItemName);
        mDataBinding.toolbar.setSubtitle("申请日期:" + mLisOrder.ReqDateTime + "   报告日期:" + mLisOrder.AuthDateTime);
        mDataBinding.listView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        View headView = LayoutInflater.from(this).inflate(R.layout.item_lis_result, null);
        mAdapter.addHeaderView(headView);
        mDataBinding.listView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
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
            mPresenter.getLisOrderListDetail(params);
            mDataBinding.toolbar.setTitle(titls.toString());
        } else {
            mPresenter.getLisOrderListDetail(mLisOrder.VisitNumberReportDR);
        }
    }

    @Override
    protected void initEvent() {
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            LisOrderDetail item = mAdapter.getItem(position);
            if (item != null && hasPreResult(item)) {
                String index = item.ResultFormat;
                switch (index) {
                    case "N":
                        showChatDialog(item);
                        break;
                    case "X":
                        break;
                    case "M":
                        break;
                    case "S":
                        break;
                }
            }
        });
    }

    private boolean hasPreResult(LisOrderDetail lisOrderDetail) {
        return StringUtils.isTrimEmpty(lisOrderDetail.PreResult);
    }


    protected void showChatDialog(LisOrderDetail order) {
        mDialogFragment = (LisChartDialogFragment) getSupportFragmentManager().findFragmentByTag(LisChartDialogFragment.TAG);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
        mDialogFragment = LisChartDialogFragment.newInstance(order);
        mDialogFragment.show(getSupportFragmentManager(), LisChartDialogFragment.TAG);
    }

    @Override
    protected LisResultPresenter initPresenter() {
        return new LisResultPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lis_result;
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
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }
}
