package szszhospital.cn.com.mobilenurse.fragemt;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.BrowsePacsImageActivity;
import szszhospital.cn.com.mobilenurse.activity.CommonWebViewActivity;
import szszhospital.cn.com.mobilenurse.activity.PdfReportActivity;
import szszhospital.cn.com.mobilenurse.adapter.PathologyOrderAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PathologyOrderContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PathologyOrderPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;

public class PathologyOrderFragment extends BaseDoctorFragment<FragmentOrderBinding, PathologyOrderPresenter> implements PathologyOrderContract.View, BaseQuickAdapter.OnItemChildClickListener {
    private static final String TAG = "PacsOrderItemFragment";

    private PathologyOrderAdapter mAdapter;

    public static PathologyOrderFragment newInstance() {
        return new PathologyOrderFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new PathologyOrderAdapter(R.layout.item_pacs_order_item);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
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
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            mPresenter.getPathologyOrderList(App.patientInfo.EpisodeID);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.top.setOnClickListener(v -> mDataBinding.orderList.scrollToPosition(0));
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mAdapter.setOnItemChildClickListener(this);
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
    public void showPacsOrderList(List<PacsOrderItem> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        PacsOrderItem item = mAdapter.getItem(position);
        Log.d(TAG, "onItemChildClick: " + position);
        int id = view.getId();
        switch (id) {
            case R.id.icon:
                handlePacsReport(adapter, item);
                break;
            case R.id.photo:
                handlePacsImage(adapter, item);
                break;
            default:
                break;
        }
    }

    private void handlePacsImage(BaseQuickAdapter adapter, PacsOrderItem item) {
        BrowsePacsImageActivity.startBrowsePacsImageActivity(_mActivity,item);
    }

    private void handlePacsReport(BaseQuickAdapter adapter, PacsOrderItem item) {
        switch (item.ReportType) {
            case "url":
                CommonWebViewActivity.startCommonWebViewActivity(_mActivity, item.PdfPath);
                break;
            case "pdf":
                PdfReportActivity.startPdfReportActivity(_mActivity,item.PdfPath,item.FTPPath);
                break;
            case "img":
                break;
            default:
                break;
        }
    }

}
