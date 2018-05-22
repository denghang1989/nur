package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.PatientListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPrescriptionBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.fragemt.PrescriptionFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispensingContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispensingPresenter;
import szszhospital.cn.com.mobilenurse.remote.model.Patient;
import szszhospital.cn.com.mobilenurse.remote.request.DispDetailListRequest;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.Drug;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfoResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SingleDrugInfoResponse;

/**
 * 配药界面Activity
 */
public class PrescriptionActivity extends BasePresentActivity<ActivityPrescriptionBinding, DispensingPresenter> implements DispensingContract.View {
    private static final String TAG         = "PrescriptionActivity";
    private static final String KEY_AUDITDR = "AuditDr";
    private String                mAuditDr;
    private DispDetailListRequest mRequest;
    private PatientListAdapter    mAdapter;
    private Set<String>                      mSet         = new HashSet<>();
    private HashMap<String, ArrayList<Drug>> mHashMap     = new HashMap<>();
    private List<Patient>                    mPatientList = new ArrayList<>();

    public static void startPrescriptionActivity(Activity context, String code) {
        Intent intent = new Intent(context, PrescriptionActivity.class);
        intent.putExtra(KEY_AUDITDR, code);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_prescription;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        mAuditDr = intent.getStringExtra(KEY_AUDITDR);
        mRequest = new DispDetailListRequest();
        mAdapter = new PatientListAdapter(R.layout.item_patient);
    }

    @Override
    protected void initView() {
        super.initView();
        loadRootFragment(R.id.container, PrescriptionFragment.newInstance(null, null));
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        if (!StringUtils.isTrimEmpty(mAuditDr)) {
            getDispInfo(mAuditDr);
        }
    }

    private void getDispInfo(String code) {
        mRequest.AuditDr = code;
        mPresenter.getDispInfo(mRequest);
    }

    @Override
    protected DispensingPresenter initPresenter() {
        return new DispensingPresenter();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            mAdapter.setSelectPosition(position);
            switchPatient(mAdapter.getItem(position).regNo);
        });

        mDataBinding.sendDrug.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiverCode(QRCodeEvent event) {
        //判断一下code的类型
        Log.d(TAG, "receiverCode: " + event.code);
        getDispInfo(event.code);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDrugBillList(List<Drug> list) {
        for (Drug drug : list) {
            mSet.add(drug.regNo);
        }

        for (String s : mSet) {
            if (!mHashMap.containsKey(s)) {
                mHashMap.put(s, new ArrayList<>());
            }

            for (Drug drug : list) {
                if (TextUtils.equals(drug.regNo, s)) {
                    List<Drug> drugs = mHashMap.get(s);
                    drugs.add(drug);
                }
            }

            Drug drug = mHashMap.get(s).get(0);
            Patient p = new Patient();
            p.bedCode = drug.bedCode;
            p.patName = drug.patName;
            p.regNo = drug.regNo;
            p.wardDesc = drug.wardDesc;
            mPatientList.add(p);
        }

        mAdapter.setNewData(mPatientList);
        switchPatient(mPatientList.get(0).regNo);
    }

    @Override
    public void setPatientInfo(PatientInfoResponse response) {

    }

    @Override
    public void setSingleDrugInfo(SingleDrugInfoResponse response) {

    }

    public void switchPatient(String patientNo) {
        ArrayList<Drug> drugList = mHashMap.get(patientNo);
        replaceFragment(PrescriptionFragment.newInstance(drugList, patientNo), false);
    }
}
