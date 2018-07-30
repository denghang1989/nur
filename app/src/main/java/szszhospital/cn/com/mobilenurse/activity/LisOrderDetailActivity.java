package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

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

public class LisOrderDetailActivity extends BasePresentActivity<ActivityLisDetailBinding, LisOrderDetailPresenter> implements LisOrderDetailContract.View {

    private static final String KEY_DATA = "data";
    private LisOrder        mLisOrder;
    private LisTableAdapter mTableViewAdapter;

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
        mPresenter.getLisOrderListDetail(mLisOrder.VisitNumberReportDR);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.toolbar.setTitle(mLisOrder.OrdItemName);
        mDataBinding.toolbar.setSubtitle("申请日期:" + mLisOrder.RecDateTime + "   报告日期:" + mLisOrder.ReqDateTime);
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
