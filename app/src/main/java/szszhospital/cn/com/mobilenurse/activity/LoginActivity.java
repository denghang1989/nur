package szszhospital.cn.com.mobilenurse.activity;

import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

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

public class LoginActivity extends BasePresentActivity<ActivityLoginBinding, LoginPresenter> implements LoginContract.View{

    private LoginSpinnerAdapter mAdapter;
    private User                mUser;
    private LoginRequest mRequest;

    @Override
    protected void init() {
        super.init();
        mAdapter = new LoginSpinnerAdapter(this);
        mUser = new User();
        mDataBinding.setUser(mUser);
        mRequest = new LoginRequest();
        mRequest.className="Nur.Android.Common";
        mRequest.methodName="logon";
        mRequest.type="Method";
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
        ToastUtils.showShort(mUser.getName() + "::" + mUser.getPassword());
        mRequest.userName = mUser.getName();
        mRequest.password = mUser.getPassword();
        mPresenter.login(mRequest);
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setSpinnerData(List<LocTable> list) {

    }
}
