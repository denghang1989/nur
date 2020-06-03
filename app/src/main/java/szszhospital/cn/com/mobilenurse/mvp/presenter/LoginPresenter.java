package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.SPUtils;

import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LoginModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public class LoginPresenter extends RxPresenter<LoginContract.View, LoginContract.Model, LoginResponse> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    @Override
    public void login(String userName, String password) {
        ApiService.Instance().getService().
                login(userName, password).
                compose(RxUtil.httpHandle()).
                compose(RxUtil.rxSchedulerHelper()).
                subscribe(this);
    }

    @Override
    public void onNext(LoginResponse loginResponse) {
        mModel.save(loginResponse);
        SPUtils.getInstance().put("user_name", loginResponse.getCode());
        mView.handleLoginResult(loginResponse);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        mView.showError(e);
    }
}
