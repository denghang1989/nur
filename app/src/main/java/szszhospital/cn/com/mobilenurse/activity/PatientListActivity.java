package szszhospital.cn.com.mobilenurse.activity;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.CardPatientAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPatientListBinding;

public class PatientListActivity extends BaseActivity<ActivityPatientListBinding> {

    private CardPatientAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_patient_list;
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new CardPatientAdapter(R.layout.item_patient_card);
    }

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
