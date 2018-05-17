package szszhospital.cn.com.mobilenurse.activity;

import android.content.Intent;
import android.view.View;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LoginSpinnerAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private LoginSpinnerAdapter mAdapter;

    @Override
    protected void init() {
        mAdapter = new LoginSpinnerAdapter(this);
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

    @Override
    protected void initEvent() {
        mDataBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
