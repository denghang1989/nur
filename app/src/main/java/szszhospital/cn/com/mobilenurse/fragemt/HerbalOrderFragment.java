package szszhospital.cn.com.mobilenurse.fragemt;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.HerbalOrderAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.HerbalOrderContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.HerbalOrderPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Order;


/**
 * 草药界面
 */
public class HerbalOrderFragment extends BaseDoctorFragment<FragmentOrderBinding, HerbalOrderPresenter> implements HerbalOrderContract.View {

    private HerbalOrderAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    public static HerbalOrderFragment newInstance() {
        return new HerbalOrderFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new HerbalOrderAdapter(R.layout.item_herbal_order);
    }

    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            mPresenter.getZOrderList("Z", App.patientInfo.EpisodeID);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.top.setOnClickListener(v -> mDataBinding.orderList.scrollToPosition(0));
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.orderList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.orderList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.orderList.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
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
    public void showOrderList(List<Order> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }
}
