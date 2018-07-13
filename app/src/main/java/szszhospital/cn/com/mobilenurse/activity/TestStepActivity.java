package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.TestStepAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityTestStepBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.TestStepContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.TestStepPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Test;
import szszhospital.cn.com.mobilenurse.remote.response.TestStep;

public class TestStepActivity extends BasePresentActivity<ActivityTestStepBinding, TestStepPresenter> implements TestStepContract.View {
    private static final String DATA = "data";
    private TestStepAdapter mAdapter;
    private Test            mTest;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_step;
    }

    public static void start(Context context, Test test) {
        Intent starter = new Intent(context, TestStepActivity.class);
        starter.putExtra(DATA, (Parcelable) test);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new TestStepAdapter(R.layout.item_test_step);
        mTest = getIntent().getParcelableExtra(DATA);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.name.setText(mTest.PatientName);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getTestStepDetail(mTest.EposideId, mTest.LabNo);
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
    public void showListView(List<TestStep> testSteps) {
        mAdapter.setNewData(testSteps);
        initStepView(testSteps);
    }

    private void initStepView(List<TestStep> testSteps) {
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("运送", 1);
        StepBean stepBean1 = new StepBean("到达", 0);
        StepBean stepBean2 = new StepBean("", -1);
        switch (testSteps.size()) {
            case 0:{
                stepBean0.setState(-1);
                stepBean1.setState(-1);
                stepBean2.setState(-1);
            }
            break;
            case 1:{
                stepBean0.setState(0);
                stepBean1.setState(-1);
                stepBean2.setState(-1);
            }
            break;
            case 2:{
                stepBean0.setState(1);
                stepBean1.setState(0);
                stepBean2.setState(-1);
            }
            break;
        }

        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);

        mDataBinding.stepView
                .setStepViewTexts(stepsBeanList)//总步骤
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(this, R.color.uncompleted_text_color))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(this, R.drawable.icon_complete))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(this, R.drawable.default_icon))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(this, R.drawable.icon_underway));//设置StepsViewIndicator AttentionIcon
    }

    @Override
    protected TestStepPresenter initPresenter() {
        return new TestStepPresenter();
    }
}
