package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.fragemt.DispensingFragment;

public class DispensingActivity extends BaseActivity {
    private static final String KEY_PRESCRIPTION = "prescription";
    private String mPrescription;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dispensing;
    }

    @Override
    protected void init() {
        super.init();
        Intent intent = getIntent();
        mPrescription = intent.getStringExtra(KEY_PRESCRIPTION);
    }

    @Override
    protected void initView() {
        super.initView();
        loadRootFragment(R.id.container, DispensingFragment.newInstance(mPrescription));
    }

    public static void startDispensingActivity(Activity context, String code) {
        Intent intent = new Intent(context, DispensingActivity.class);
        intent.putExtra(KEY_PRESCRIPTION, code);
        context.startActivity(intent);
    }
}
