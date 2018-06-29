package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.databinding.User;
import szszhospital.cn.com.mobilenurse.entity.LocTable;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SchDateTimeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public interface LoginContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setSpinnerData(List<LocTable> list);

        void goToMainActivity();
    }

    interface Model extends BaseModel {
        void save(LoginResponse loginResponse);
    }

    interface Presenter extends BasePresenter<View> {
        void login(LoginRequest request, User user);

        void clearCacheDateTime(SchDateTimeRequest request);
    }
}
