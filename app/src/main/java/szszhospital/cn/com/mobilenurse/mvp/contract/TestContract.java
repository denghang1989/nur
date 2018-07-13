package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.Test;

public interface TestContract {

    interface View extends BaseView {

        void showProgress();

        void hideProgress();

        void showListView(Test test);
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getLisNoInfo(String LisNo,String UserID);
    }
}
