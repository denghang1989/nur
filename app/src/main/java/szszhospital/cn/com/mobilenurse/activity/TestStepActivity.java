package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityTestStepBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.TestStepContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.TestStepPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Test;
import szszhospital.cn.com.mobilenurse.remote.response.TestStep;

public class TestStepActivity extends BasePresentActivity<ActivityTestStepBinding,TestStepPresenter> implements TestStepContract.View {
    private static final String DATA = "data";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_step;
    }

    public static void start(Context context,Test test) {
        Intent starter = new Intent(context, TestStepActivity.class);
        starter.putExtra(DATA, (Parcelable) test);
        context.startActivity(starter);
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showListView(List<TestStep> testSteps) {

    }
}
