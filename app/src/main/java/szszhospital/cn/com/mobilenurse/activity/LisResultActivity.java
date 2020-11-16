package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LisResultAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLisResultBinding;
import szszhospital.cn.com.mobilenurse.dialog.DrugAllergyFragment;
import szszhospital.cn.com.mobilenurse.dialog.LisChartDialogFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LisResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

/**
 * @author admin
 */
public class LisResultActivity extends BasePresentActivity<ActivityLisResultBinding, LisResultPresenter> implements LisResultContract.View {

    private static final String                 TAG  = "LisResultActivity";
    private static final String                 DATA = "data";
    private              LisOrder               mLisOrder;
    private              LisResultAdapter       mAdapter;
    private              LisChartDialogFragment mDialogFragment;
    private              DrugAllergyFragment    mDrugAllergyFragment;

    public static void startLisResultActivity(Context context, LisOrder lisOrder) {
        Intent intent = new Intent(context, LisResultActivity.class);
        intent.putExtra(DATA, lisOrder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mLisOrder = (LisOrder) getIntent().getSerializableExtra(DATA);
        mAdapter = new LisResultAdapter(R.layout.item_lis_result);
    }

    @Override
    protected void initView() {
        mDataBinding.toolbar.setTitle(mLisOrder.OrdItemName);
        mDataBinding.toolbar.setSubtitle("申请日期:" + mLisOrder.ReqDateTime + "   报告日期:" + mLisOrder.AuthDateTime);
        mDataBinding.listView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        View headView = LayoutInflater.from(this).inflate(R.layout.item_lis_result, null);
        mAdapter.addHeaderView(headView);
        mDataBinding.listView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getLisOrderListDetail(mLisOrder.VisitNumberReportDR);
    }

    @Override
    protected void initEvent() {
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            LisOrderDetail item = mAdapter.getItem(position);
            item.ReportDR = mLisOrder.VisitNumberReportDR;
            String index = item.ResultFormat;
            switch (index) {
                case "N":
                    if (hasPreResult(item)) {
                        showChatDialog(item);
                    }
                    break;
                case "X":
                    break;
                case "M":
                    showDrugAllergyDialog(item);
                    break;
                case "S":
                    break;
                default:
                    break;
            }
        });
    }

    private boolean hasPreResult(LisOrderDetail lisOrderDetail) {
        return !StringUtils.isTrimEmpty(lisOrderDetail.PreResult);
    }

    protected void showChatDialog(LisOrderDetail order) {
        mDialogFragment = (LisChartDialogFragment) getSupportFragmentManager().findFragmentByTag(LisChartDialogFragment.TAG);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
        mDialogFragment = LisChartDialogFragment.newInstance(order);
        mDialogFragment.show(getSupportFragmentManager(), LisChartDialogFragment.TAG);
    }

    protected void showDrugAllergyDialog(LisOrderDetail order) {
        mDrugAllergyFragment = (DrugAllergyFragment) getSupportFragmentManager().findFragmentByTag(DrugAllergyFragment.TAG);
        if (mDrugAllergyFragment != null) {
            mDrugAllergyFragment.dismiss();
        }
        mDrugAllergyFragment = DrugAllergyFragment.newInstance(order);
        mDrugAllergyFragment.show(getSupportFragmentManager(), DrugAllergyFragment.TAG);
    }

    @Override
    protected LisResultPresenter initPresenter() {
        return new LisResultPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lis_result;
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
    public void showLisOrderListDetail(List<LisOrderDetail> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }

        if (mDrugAllergyFragment != null) {
            mDrugAllergyFragment.dismiss();
        }
    }
}
