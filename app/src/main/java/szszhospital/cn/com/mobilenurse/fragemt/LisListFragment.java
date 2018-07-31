package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.LisOrderDetailActivity;
import szszhospital.cn.com.mobilenurse.adapter.LisOrderListAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LisOrderPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

/**
 * Lis检验报告
 */
public class LisListFragment extends BaseDoctorFragment<FragmentOrderBinding, LisOrderPresenter> implements LisOrderContract.View {
    private static final String TAG = "LisListFragment";

    private LisOrderListAdapter mAdapter;

    public static LisListFragment newInstance() {
        return new LisListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new LisOrderListAdapter(R.layout.item_lis_order);
    }

    @Override
    protected void initView() {
        super.initView();
        LinearLayoutManager layout = new LinearLayoutManager(_mActivity);
        mDataBinding.orderList.setLayoutManager(layout);
        mDataBinding.orderList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.orderList.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.top.setOnClickListener(v -> mDataBinding.orderList.scrollToPosition(0));
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LisOrder lisOrder = mAdapter.getItem(position);
                if (StringUtils.equals("3", lisOrder.ResultStatus)) {
                    LisOrderDetailActivity.startLisOrderDetailActivity(_mActivity, lisOrder);
                } else {
                    ToastUtils.showShort("无报告！");
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            mPresenter.getLisOrderList(App.patientInfo.PatientID, App.patientInfo.EpisodeID);
        }
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
    public void showLisOrderList(List<LisOrder> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    protected LisOrderPresenter initPresenter() {
        return new LisOrderPresenter();
    }
}
