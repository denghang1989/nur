package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.PacsResultActivity;
import szszhospital.cn.com.mobilenurse.activity.PacsWebViewDetailActivity;
import szszhospital.cn.com.mobilenurse.activity.PacsImageActivity;
import szszhospital.cn.com.mobilenurse.activity.PdfFromHttpActivity;
import szszhospital.cn.com.mobilenurse.adapter.PacsResultAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsListContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsListPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.AppUtil;

/**
 * PACS检查报告
 */
public class PacsListFragment extends BaseDoctorFragment<FragmentOrderBinding, PacsListPresenter> implements PacsListContract.View {

    private PacsResultAdapter mAdapter;

    public static PacsListFragment newInstance() {
        return new PacsListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected PacsListPresenter initPresenter() {
        return new PacsListPresenter();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new PacsResultAdapter(R.layout.item_pacs_order);
    }

    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            String userCode = SPUtils.getInstance().getString("user_name");
            mPresenter.getPacsOrderList(App.patientInfo.EpisodeID, userCode);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.top.setOnClickListener(v -> mDataBinding.orderList.scrollToPosition(0));
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            PacsOrder pacsOrder = mAdapter.getItem(position);
            int id = view.getId();
            switch (id) {
                case R.id.icon:
                    //pdf
                    if (!StringUtils.isTrimEmpty(pacsOrder.TPdfPath)) {
                        PdfFromHttpActivity.startPdfActivity(_mActivity, pacsOrder.TPdfPath);
                        return;
                    }
                    //http
                    if (AppUtil.IsUrl(mAdapter.getPath(pacsOrder))) {
                        PacsWebViewDetailActivity.startPacsDetailActivity(_mActivity, pacsOrder);
                        return;
                    }
                    //超声影像科报告
                    if (StringUtils.equals(pacsOrder.TreplocDr, "14")) {
                        PacsResultActivity.startPacsResultActivity(_mActivity, pacsOrder);
                        return;
                    }
                    break;
                case R.id.photo:
                    PacsImageActivity.startImageActivity(_mActivity, pacsOrder);
                    break;
            }
        });
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
    public void showPacsOrderList(List<PacsOrder> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }

}
