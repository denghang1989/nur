package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.mode.LocTable;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;

public interface LoginContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setSpinnerData(List<LocTable> list);
    }

    interface Model extends BaseModel {
        void saveLoginUser();

        void saveLoc();
    }

    interface Presenter extends BasePresenter<View> {
        void login(LoginRequest request);

        void clearCacheDateTime();
    }
}
