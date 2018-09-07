package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.UpdateApp;

public interface MainContract {
    interface View extends BaseView {
        void showDialog(UpdateApp updateApp);
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getUpdateApp();
    }
}
