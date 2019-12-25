package szszhospital.cn.com.mobilenurse.fragemt;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.OrderListAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.dialog.OrderExtDialogFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.OrderContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.OrderPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Order;
import szszhospital.cn.com.mobilenurse.utils.OrderItemDecoration;

/**
 * 临时医嘱
 */
public abstract class BaseOrdersFragment extends BaseDoctorFragment<FragmentOrderBinding, OrderPresenter> implements OrderContract.View {
    private static final String TAG = "BaseOrdersFragment";
    protected OrderListAdapter       mAdapter;
    protected OrderExtDialogFragment mDialogFragment;
    protected List<Order>            mOrderList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new OrderListAdapter(getOrderList());
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
            mPresenter.getPatientOrderList(getOrderType(),App.patientInfo.EpisodeID);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mAdapter.setOnItemClickListener((adapter, view, position) -> showDialog(mAdapter.getItem(position)));
        mDataBinding.orderList.addItemDecoration(new OrderItemDecoration(mAdapter, _mActivity));
        mDataBinding.orderList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    showTextView();
                } else {
                    hideTextView();
                }
            }
        });
    }

    private void hideTextView() {
        int span = ConvertUtils.dp2px(10) + mDataBinding.top.getHeight();
        ViewAnimator.animate(mDataBinding.top)
                .translationY(span)
                .duration(250)
                .start();
    }

    private void showTextView() {
        ViewAnimator.animate(mDataBinding.top)
                .translationY(0)
                .duration(250)
                .start();
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
        mOrderList = list;
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

    protected void showDialog(Order order) {
        mDialogFragment = (OrderExtDialogFragment) getChildFragmentManager().findFragmentByTag(OrderExtDialogFragment.TAG);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }

        mDialogFragment = OrderExtDialogFragment.newInstance(order);
        mDialogFragment.show(getChildFragmentManager(), OrderExtDialogFragment.TAG);
    }

    public List<Order> getOrderList() {
        return mOrderList == null ? new ArrayList<>() : mOrderList;
    }
}
