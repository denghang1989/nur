package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.ImageActivity;
import szszhospital.cn.com.mobilenurse.activity.PacsDetailActivity;
import szszhospital.cn.com.mobilenurse.adapter.PacsResultAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentOrderBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

/**
 * PACS检查报告
 */
public class PacsResultFragment extends BaseDoctorFragment<FragmentOrderBinding, PacsResultPresenter> implements PacsResultContract.View {

    private PacsResultAdapter mAdapter;

    public static PacsResultFragment newInstance() {
        return new PacsResultFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected PacsResultPresenter initPresenter() {
        return new PacsResultPresenter();
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
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            PacsOrder pacsOrder = mAdapter.getItem(position);
            String memo = pacsOrder.Memo;
            if (StringUtils.equals(memo, "S^已发布")) {
                PacsDetailActivity.startPacsDetailActivity(_mActivity, pacsOrder);
            } else {
                ToastUtils.showShort("无报告");
            }
        });

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            int id = view.getId();
            PacsOrder pacsOrder = mAdapter.getItem(position);
            switch (id) {
                case R.id.icon:
                    PacsDetailActivity.startPacsDetailActivity(_mActivity, pacsOrder);
                    break;
                case R.id.photo:
                    ImageActivity.startImageActivity(_mActivity, pacsOrder);
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
