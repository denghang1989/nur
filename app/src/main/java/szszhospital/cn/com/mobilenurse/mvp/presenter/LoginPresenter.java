package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LoginModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public class LoginPresenter extends RxPresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    @Override
    public void login(String userName, String password) {
        mView.showProgress();
        ApiService.Instance().getService().
                login(userName, password).
                compose(RxUtil.httpHandle()).
                compose(RxUtil.rxSchedulerHelper()).
                subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mModel.save(loginResponse);
                        SPUtils.getInstance().put("user_name", loginResponse.getName());
                        mView.goToMainActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        ToastUtils.showShort("账号或者密码错误");
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

}
