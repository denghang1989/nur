package szszhospital.cn.com.mobilenurse.activity;

import android.util.Log;
import android.view.View;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LoginSpinnerAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLoginBinding;
import szszhospital.cn.com.mobilenurse.databinding.User;
import szszhospital.cn.com.mobilenurse.mode.LocTable;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LoginPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;

public class LoginActivity extends BasePresentActivity<ActivityLoginBinding, LoginPresenter> implements LoginContract.View {
    private static final String TAG = "LoginActivity";
    private LoginSpinnerAdapter mAdapter;
    private User                mUser;
    private LoginRequest        mRequest;

    @Override
    protected void init() {
        super.init();
        mAdapter = new LoginSpinnerAdapter(this);
        mUser = new User();
        mDataBinding.setUser(mUser);
        initLoginRequest();
    }

    private void initLoginRequest() {
        mRequest = new LoginRequest();
        mRequest.className = "Nur.Android.Common";
        mRequest.methodName = "logon";
        mRequest.type = "Method";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
        mDataBinding.loc.setAdapter(mAdapter);
        mDataBinding.loc.setSelection(0,true);
    }

    public void onClick(View view) {
        mRequest.userName = mUser.getName();
        mRequest.password = mUser.getPassword();
        Log.d(TAG, "onClick: "+mRequest.toString());
        mPresenter.login(mRequest);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showProgress() {
        mDataBinding.login.setEnabled(false);
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.login.setEnabled(true);
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void setSpinnerData(List<LocTable> list) {
        mAdapter.setData(list);
    }
}
