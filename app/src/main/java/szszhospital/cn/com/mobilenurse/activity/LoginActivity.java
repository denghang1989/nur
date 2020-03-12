package szszhospital.cn.com.mobilenurse.activity;

import android.view.View;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

import es.dmoral.toasty.Toasty;
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
        if (!checkForm(userName, password)) {
            mPresenter.login(userName, password);
        } else {
            Toasty.warning(this, "请输入账号和密码", Toast.LENGTH_SHORT, true).show();
        }
    }

    private boolean checkForm(String userName, String password) {
        return StringUtils.isTrimEmpty(userName) || StringUtils.isTrimEmpty(password);
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
    public void showError() {
        Toasty.error(this, "账号或密码错误", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void goToMainActivity() {
        Toasty.success(this, "登入成功!", Toast.LENGTH_SHORT, true).show();
        ActivityUtils.startActivity(MainActivity.class);
        finish();
    }

}
