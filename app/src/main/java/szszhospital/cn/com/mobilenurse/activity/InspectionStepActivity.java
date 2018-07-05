package szszhospital.cn.com.mobilenurse.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.InspectionStepAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityInspectionStepBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionStepContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.InspectionStepPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.response.InspectionLogDetail;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionStepActivity extends BasePresentActivity<ActivityInspectionStepBinding, InspectionStepPresenter> implements InspectionStepContract.View {
    private static final String KEY_DATA       = "data";
    private static final String KEY_EPISODE_ID = "eposideId";
    private PacsOrderSubscribe    mPacsOrderSubscribe;
    private InspectionRequest     mRequest;
    private InspectionStepAdapter mAdapter;

    public static void startInspectionStepActivity(Activity context, PacsOrderSubscribe orderSubscribe, String episodeId) {
        Intent intent = new Intent(context, InspectionStepActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) orderSubscribe);
        intent.putExtra(KEY_EPISODE_ID, episodeId);
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
    public void showDetails(List<InspectionLogDetail> inspectionLogDetails) {
        mAdapter.setNewData(inspectionLogDetails);
        initStepView(inspectionLogDetails);
    }

    @Override
    protected void init() {
        super.init();
        mRequest = new InspectionRequest();
        mAdapter = new InspectionStepAdapter(R.layout.item_inspection_step);
        mPacsOrderSubscribe = getIntent().getParcelableExtra(KEY_DATA);
        mRequest.EpisodeID = getIntent().getStringExtra(KEY_EPISODE_ID);
        mRequest.ARRepID = mPacsOrderSubscribe.arRepID;
        mRequest.UserID = App.loginUser.UserDR;
        mRequest.Action = "query";
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.name.setText(mPacsOrderSubscribe.PatName);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    private void initStepView(List<InspectionLogDetail> inspectionLogDetails) {
        List<StepBean> stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("离开病房", 1);
        StepBean stepBean1 = new StepBean("检查", 0);
        StepBean stepBean2 = new StepBean("回到病房", -1);
        switch (inspectionLogDetails.size()) {
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
            case 3:{
                stepBean0.setState(1);
                stepBean1.setState(1);
                stepBean2.setState(0);
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
    protected void initData() {
        super.initData();
        mPresenter.getPacsOrderDetail(mRequest);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected InspectionStepPresenter initPresenter() {
        return new InspectionStepPresenter();
    }
}
