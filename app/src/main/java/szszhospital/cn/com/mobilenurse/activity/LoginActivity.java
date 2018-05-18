package szszhospital.cn.com.mobilenurse.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.blankj.utilcode.util.KeyboardUtils;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LoginSpinnerAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLoginBinding;
import szszhospital.cn.com.mobilenurse.databinding.User;
import szszhospital.cn.com.mobilenurse.db.LocTable;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LoginPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SchDateTimeRequest;

public class LoginActivity extends BasePresentActivity<ActivityLoginBinding, LoginPresenter> implements LoginContract.View {
    private static final String TAG = "LoginActivity";
    private LoginSpinnerAdapter mAdapter;
    private User                mUser;
    private LoginRequest        mRequest;
    private SchDateTimeRequest  mSchDateTimeRequest;

    @Override
    protected void init() {
        super.init();
        mAdapter = new LoginSpinnerAdapter(this);
        mUser = new User();
        mDataBinding.setUser(mUser);
        initLoginRequest();
        initSchDateTimeRequest();
    }

    private void initSchDateTimeRequest() {
        mSchDateTimeRequest = new SchDateTimeRequest();
    }

    private void initLoginRequest() {
        mRequest = new LoginRequest();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
        mDataBinding.loc.setAdapter(mAdapter);
    }

    public void onClick(View view) {
        if (!mUser.isLogin()) {
            mRequest.userName = mUser.getName();
            mRequest.password = mUser.getPassword();
            mPresenter.login(mRequest, mUser);
        } else {
            mSchDateTimeRequest.user = App.loginUser.UserDR;
            mPresenter.clearCacheDateTime(mSchDateTimeRequest);
        }
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showProgress() {
        if (KeyboardUtils.isSoftInputVisible(this)) {
            KeyboardUtils.hideSoftInput(this);
        }
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
        mDataBinding.loc.setSelection(0);
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void initEvent() {
        mDataBinding.loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                App.loginUser.UserLoc = mAdapter.getItem(position).LocID;
                App.loginUser.WardID = mAdapter.getItem(position).WardID;
                App.loginUser.UserGroupID = mAdapter.getItem(position).GroupID;
                App.loginUser.UserLocDesc = mAdapter.getItem(position).LocDesc;
                App.loginUser.UserID = mUser.getName();
                if (KeyboardUtils.isSoftInputVisible(LoginActivity.this)) {
                    KeyboardUtils.hideSoftInput(LoginActivity.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
