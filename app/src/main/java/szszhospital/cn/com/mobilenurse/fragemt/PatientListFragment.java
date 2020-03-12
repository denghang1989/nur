package szszhospital.cn.com.mobilenurse.fragemt;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.MainActivity;
import szszhospital.cn.com.mobilenurse.adapter.IPatientListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentPatientListBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.event.SwitchLocEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.PatientListContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PatientListPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;
import szszhospital.cn.com.mobilenurse.viewholder.PatientViewHolder;

/**
 * 病人列表
 */
public class PatientListFragment extends BasePresenterFragment<FragmentPatientListBinding, PatientListPresenter> implements PatientListContract.View {

    private static final String TAG = "PatientListFragment";
    private IPatientListAdapter mAdapter;
    private PatientViewHolder   mPatientViewHolder;
    private int mSelectPatient = -1;

    public static PatientListFragment newInstance() {
        return new PatientListFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_patient_list;
    }

    @Override
    public void showPatientList(List<PatientInfo> list) {
        if (list.size() > 0) {
            PatientInfo patientInfo = list.get(0);
            App.setPatientInfo(patientInfo);
            mSelectPatient = 0;
            mAdapter.setSelected(mSelectPatient + mAdapter.getHeaderLayoutCount());
            mPatientViewHolder.setData(patientInfo);
            EventBus.getDefault().post(new SelectPatientEvent(patientInfo));
        }
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
        mPatientViewHolder = new PatientViewHolder(_mActivity, R.layout.item_patient_head);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.refreshLayout.requestDisallowInterceptTouchEvent(true);
        mDataBinding.patientList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.patientList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.patientList.setAdapter(mAdapter);
        mAdapter.addHeaderView(mPatientViewHolder.getConvertView());
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected PatientListPresenter initPresenter() {
        return new PatientListPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPatientList(App.loginUser.UserDR,App.loginUser.UserLoc);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            PatientInfo patientInfo = mAdapter.getItem(position);
            mAdapter.setSelected(position + mAdapter.getHeaderLayoutCount());
            App.setPatientInfo(patientInfo);
            ((MainActivity) _mActivity).closeDrawer();
            mPatientViewHolder.setData(patientInfo);
            if (position != mSelectPatient) {
                EventBus.getDefault().post(new SelectPatientEvent(patientInfo));
            }
            mSelectPatient = position;
        });

        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectedPatient(SwitchLocEvent event) {
        if (!StringUtils.isTrimEmpty(event.locId)) {
            initData();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
