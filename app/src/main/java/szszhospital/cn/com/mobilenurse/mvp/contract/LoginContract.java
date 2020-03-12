package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public interface LoginContract {
    interface View extends BaseView {

        void showError();

        void goToMainActivity();
    }

    interface Model extends BaseModel {
        void save(LoginResponse loginResponse);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String userName, String password);
    }
}
