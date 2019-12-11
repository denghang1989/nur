package szszhospital.cn.com.mobilenurse.activity;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityLoginBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LoginPresenter;

/**
 * @author admin
 */
public class LoginActivity extends BasePresentActivity<ActivityLoginBinding, LoginPresenter> implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @Override
    protected void init() {
        super.init();
        String userName = SPUtils.getInstance().getString("user_name");
        mDataBinding.userName.setText(userName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
    }

    public void onClick(View view) {
        String userName = mDataBinding.userName.getText().toString().trim();
        String password = mDataBinding.userPassword.getText().toString().trim();
        if (!checkForm(userName,password)) {
            mPresenter.login(userName,password);
        } else {
            ToastUtils.showShort("请输入账号和密码!");
        }
    }

    private boolean checkForm(String userName,String password) {
        return StringUtils.isTrimEmpty(userName) && StringUtils.isTrimEmpty(password);
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
    public void goToMainActivity() {
        ActivityUtils.startActivity(MainActivity.class);
        finish();
    }

}
