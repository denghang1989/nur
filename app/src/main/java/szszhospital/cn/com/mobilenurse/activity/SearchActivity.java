package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.SearchView;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivitySearchBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.SearchContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.SearchPresenter;

public class SearchActivity extends BasePresentActivity<ActivitySearchBinding, SearchPresenter> implements SearchContract.View {

    public static void startSearchActivity(Activity activity) {
        activity.startActivity(new Intent(activity, SearchActivity.class));
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
        mDataBinding.toolbar.setNavigationOnClickListener(view -> finish());

        mDataBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void initView() {
        mDataBinding.searchView.setSubmitButtonEnabled(true);
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
    public void showData() {

    }
}
