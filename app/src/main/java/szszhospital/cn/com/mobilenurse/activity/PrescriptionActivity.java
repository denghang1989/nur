package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.PatientListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPrescriptionBinding;
import szszhospital.cn.com.mobilenurse.fragemt.PrescriptionFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailListContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispDetailListPresenter;
import szszhospital.cn.com.mobilenurse.remote.model.Patient;
import szszhospital.cn.com.mobilenurse.remote.request.DispDetailListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

/**
 * 配药明细界面Activity
 */
public class PrescriptionActivity extends BasePresentActivity<ActivityPrescriptionBinding, DispDetailListPresenter> implements DispDetailListContract.View {
    private static final String TAG         = "PrescriptionActivity";
    private static final String KEY_AUDITDR = "AuditDr";
    private String                mAuditDr;
    private DispDetailListRequest mRequest;
    private PatientListAdapter    mAdapter;
    private ArrayList<DispDetailResponse> mList = new ArrayList<>(0);

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
        mPresenter.getDispDetailList(mRequest);
    }

    @Override
    protected DispDetailListPresenter initPresenter() {
        return new DispDetailListPresenter();
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
    public void showProgress() {
        mDataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDispDetailList(List<DispDetailResponse> list) {
        mList.clear();
        List<Patient> patients = Stream.of(list).
                distinctBy(dispDetailResponse -> dispDetailResponse.PatName)
                .map(dispDetailResponse -> {
                    Patient patient = new Patient();
                    patient.regNo = dispDetailResponse.PatNo;
                    patient.patName = dispDetailResponse.PatName;
                    patient.age = dispDetailResponse.Age;
                    patient.sex = dispDetailResponse.Sex;
                    return patient;
                }).collect(Collectors.toList());
        mAdapter.setNewData(patients);
        mList.addAll(list);
        switchPatient(patients.get(0).regNo);
    }

    public void switchPatient(String patientNo) {
        replaceFragment(PrescriptionFragment.newInstance(mList, patientNo), false);
    }
}
