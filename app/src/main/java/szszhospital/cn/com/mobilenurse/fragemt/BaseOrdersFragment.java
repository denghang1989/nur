package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.OrderListAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.OrderContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.OrderPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.OrderRequest;
import szszhospital.cn.com.mobilenurse.remote.response.Order;
import szszhospital.cn.com.mobilenurse.view.OrderExtDialogFragment;

/**
 * 临时医嘱
 */
public abstract class BaseOrdersFragment extends BaseDoctorFragment<FragmentOrderBinding, OrderPresenter> implements OrderContract.View {
    private static final String TAG = "BaseOrdersFragment";
    protected OrderRequest           mOrderRequest;
    protected OrderListAdapter       mAdapter;
    protected OrderExtDialogFragment mDialogFragment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void init() {
        super.init();
        mOrderRequest = new OrderRequest();
        mOrderRequest.OrderType = getOrderType();
        mAdapter = new OrderListAdapter(R.layout.item_order);
    }

    @Override
    protected void initView() {
        mDataBinding.orderList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.orderList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.orderList.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initData() {
        if (App.patientInfo != null) {
            mOrderRequest.EposideId = App.patientInfo.EpisodeID;
            mPresenter.getPatientOrderList(mOrderRequest);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.top.setOnClickListener(v -> mDataBinding.orderList.scrollToPosition(0));
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mAdapter.setOnItemClickListener((adapter, view, position) -> shwoDialog(mAdapter.getItem(position)));
    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void showPatientOrderList(List<Order> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    protected OrderPresenter initPresenter() {
        return new OrderPresenter();
    }

    protected abstract String getOrderType();

    protected void shwoDialog(Order order) {
        mDialogFragment = (OrderExtDialogFragment) getChildFragmentManager().findFragmentByTag(OrderExtDialogFragment.TAG);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }

        mDialogFragment = OrderExtDialogFragment.newInstance(order);
        mDialogFragment.show(getChildFragmentManager(), OrderExtDialogFragment.TAG);
    }
}
