package szszhospital.cn.com.mobilenurse.mvp.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public class LoginPresenter extends RxPresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    @Override
    public void login(final LoginRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().
                login(obj2Map(request)).
                compose(RxUtil.<LoginResponse>rxSchedulerHelper()).
                subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mView.setSpinnerData(loginResponse.Locs);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });

    }

    @Override
    public void clearCacheDateTime() {

    }
}
