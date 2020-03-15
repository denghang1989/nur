package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.EposideVitalSignsAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.EposideVitalSignsContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.EposideVitalSignsPresenter;

/**
 * 体温单图像浏览
 */
public class EposideVitalSignsFragment extends BasePresenterFragment<FragmentOrderBinding, EposideVitalSignsPresenter> implements EposideVitalSignsContract.View {

    public static EposideVitalSignsFragment newInstance() {
        return new EposideVitalSignsFragment();
    }

    private EposideVitalSignsAdapter mAdapter;

    @Override
    protected void init() {
        super.init();
        mAdapter = new EposideVitalSignsAdapter(R.layout.item_emr_image);
    }

    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            mPresenter.getImageList(App.patientInfo.EpisodeID);
        }
    }

    @Override
    protected void initView() {
        super.initView();
        initRecyclerView(mDataBinding.orderList, mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
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
    public void showOrderList(List<String> list) {
        mDataBinding.showData.setVisibility(View.GONE);
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
    }

    @Override
    public void showEmptyData() {
        mDataBinding.showData.setVisibility(View.VISIBLE);
    }
}
