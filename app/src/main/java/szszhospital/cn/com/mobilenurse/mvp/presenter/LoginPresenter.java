package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.databinding.User;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LoginModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SchDateTimeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SchDateTimeResponse;

public class LoginPresenter extends RxPresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

    private static final String TAG = "LoginPresenter";

    public LoginPresenter() {
        mModel = new LoginModel();
    }

    @Override
    public void login(final LoginRequest request, final User user) {
        mView.showProgress();
        ApiService.Instance().getService().
                login(obj2Map(request)).
                compose(RxUtil.<Response<LoginResponse>>rxSchedulerHelper()).
                compose(RxUtil.<LoginResponse>httpHandleResponse()).
                subscribe(new Observer<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mView.setSpinnerData(loginResponse.Locs);
                        mModel.save(loginResponse);
                        user.setLogin(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });

    }

    @Override
    public void clearCacheDateTime(SchDateTimeRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().clearCacheLogin(obj2Map(request))
                .compose(RxUtil.<Response<SchDateTimeResponse>>rxSchedulerHelper())
                .compose(RxUtil.<SchDateTimeResponse>httpHandleResponse()).subscribe(new Observer<SchDateTimeResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                addSubscribe(d);
            }

            @Override
            public void onNext(SchDateTimeResponse response) {
                ToastUtils.showShort("登入成功");
                String[] itmst = response.stdatetime.split(",");
                String[] itmet = response.enddatetime.split(",");
                App.loginUser.SchStDate = itmst[0];
                App.loginUser.StTime = itmst[1];
                App.loginUser.SchEnDate = itmet[0];
                App.loginUser.EnTime = itmet[1];
                mView.goToMainActivity();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.hideProgress();
            }

            @Override
            public void onComplete() {
                mView.hideProgress();
            }
        });
    }

}
