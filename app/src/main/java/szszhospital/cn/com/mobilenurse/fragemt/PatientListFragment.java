package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.IPatientListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentPatientListBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.PatientListContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PatientListPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.PatientListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class PatientListFragment extends BasePresenterFragment<FragmentPatientListBinding, PatientListPresenter> implements PatientListContract.View {

    private IPatientListAdapter mAdapter;
    private PatientListRequest  mRequest;

    public static PatientListFragment newInstance() {
        return new PatientListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patient_list;
    }

    @Override
    public void showPatientList(List<PatientInfo> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new IPatientListAdapter(R.layout.item_patient_list);
        mRequest = new PatientListRequest();
        setSwipeBackEnable(false);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.refreshLayout.requestDisallowInterceptTouchEvent(true);
        mDataBinding.patientList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.patientList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.patientList.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected PatientListPresenter initPresenter() {
        return new PatientListPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mRequest.LocID = App.loginUser.UserLoc;
        mRequest.UserId = App.loginUser.UserDR;
        mPresenter.getPatientList(mRequest);
    }

    private static final String TAG = "PatientListFragment";

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            EventBus.getDefault().post(new SelectPatientEvent(mAdapter.getItem(position)));
            mAdapter.setSelected(position);
        });
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
    }
}
