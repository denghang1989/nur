package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
    protected SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initEvent() {
        mDataBinding.back.setOnClickListener(view -> finish());

        mDataBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (RegexUtils.isMatch("^\\d{8}$", query)) {
                    mPresenter.searchByNo(query);
                } else {
                    ToastUtils.showShort("输入不正确！！");
                }
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
            mPresenter.searchByNo(String.format("%08d", Integer.parseInt(App.patientInfo.PatientNo))+"");
        }
    }

    @Override
    public void showData(List<PatientInfo> patientInfos) {
        mAdapter.setNewData(patientInfos);
    }
}
