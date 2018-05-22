package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDispensingBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispensingContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispensingPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Drug;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfoResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SingleDrugInfoResponse;

/**
 * 配药界面Fragment
 */
public class PrescriptionFragment extends BasePresenterFragment<FragmentDispensingBinding, DispensingPresenter> implements DispensingContract.View {
    private static final String TAG            = "DispensingFragment";
    private static final String KEY_DATA       = "data";
    private static final String KEY_PATIENT_NO = "key_patient_no";
    private DrugListAdapter mAdapter;
    private String          mPatientNo;

    public static PrescriptionFragment newInstance(ArrayList<Drug> drugList, String patientNo) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA, drugList);
        args.putString(KEY_PATIENT_NO, patientNo);
        PrescriptionFragment fragment = new PrescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dispensing;
    }

    @Override
    protected void init() {
        super.init();
        setSwipeBackEnable(false);
        mPatientNo = getArguments().getString(KEY_PATIENT_NO);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.drugList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.drugList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mAdapter = new DrugListAdapter(R.layout.item_drug);
        mDataBinding.drugList.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() {
        mDataBinding.drugProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.drugProgress.setVisibility(View.GONE);
    }

    @Override
    public void setDrugBillList(List<Drug> list) {
        mAdapter.setNewData(list);
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<Drug> list = getArguments().getParcelableArrayList(KEY_DATA);
        if (list != null) {
            setDrugBillList(list);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void setPatientInfo(PatientInfoResponse response) {

    }

    @Override
    public void setSingleDrugInfo(SingleDrugInfoResponse response) {

    }
}
