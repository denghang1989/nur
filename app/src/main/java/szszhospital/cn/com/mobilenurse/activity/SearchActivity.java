package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.SearchAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivitySearchBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.SearchContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.SearchPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class SearchActivity extends BasePresentActivity<ActivitySearchBinding, SearchPresenter> implements SearchContract.View {
    private static final String TAG = "SearchActivity";

    private SearchAdapter mAdapter;

    public static void startSearchActivity(Activity activity) {
        activity.startActivity(new Intent(activity, SearchActivity.class));
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new SearchAdapter(R.layout.item_patient_card);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initEvent() {
        mDataBinding.back.setOnClickListener(view -> finish());

        mDataBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.searchByNo(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: " + newText);
                return true;
            }
        });

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            PatientInfo patientInfo = mAdapter.getItem(position);
            App.setPatientInfo(patientInfo);
            EventBus.getDefault().post(new SelectPatientEvent(patientInfo));
            finish();
        });
    }

    @Override
    protected void initView() {
        mDataBinding.searchView.setSubmitButtonEnabled(true);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        if (App.patientInfo != null) {
            mPresenter.searchByNo(App.patientInfo.PatientNo);
        }
    }

    @Override
    public void showData(List<PatientInfo> patientInfos) {
        mAdapter.setNewData(patientInfos);
    }
}
