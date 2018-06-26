package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LisOrderDetailAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLisDetailBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderDetailContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LisOrderDetailPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisOrderDetailActivity extends BasePresentActivity<ActivityLisDetailBinding, LisOrderDetailPresenter> implements LisOrderDetailContract.View {

    private static final String KEY_DATA = "data";
    private LisOrder              mLisOrder;
    private LisOrderDetailAdapter mAdapter;

    public static void startLisOrderDetailActivity(Context context, LisOrder lisOrder) {
        Intent intent = new Intent(context, LisOrderDetailActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) lisOrder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mLisOrder = getIntent().getParcelableExtra(KEY_DATA);
        mAdapter = new LisOrderDetailAdapter(R.layout.item_lis_order_detail);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getLisOrderListDetail(mLisOrder.VisitNumberReportDR);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.list.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.list.setAdapter(mAdapter);
        mDataBinding.toolbar.setTitle(mLisOrder.OrdItemName);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lis_detail;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLisOrderListDetail(List<LisOrderDetail> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {

    }

    @Override
    protected LisOrderDetailPresenter initPresenter() {
        return new LisOrderDetailPresenter();
    }
}
