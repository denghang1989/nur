package szszhospital.cn.com.mobilenurse.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityInspectionStepBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionStepContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.InspectionStepPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionStepActivity extends BasePresentActivity<ActivityInspectionStepBinding, InspectionStepPresenter> implements InspectionStepContract.View {
    private static final String KEY_DATA = "data";

    public static void startInspectionStepActivity(Activity context, PacsOrderSubscribe orderSubscribe) {
        Intent intent = new Intent(context, InspectionStepActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) orderSubscribe);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_step;
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
    protected void init() {
        super.init();
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
    }
}
