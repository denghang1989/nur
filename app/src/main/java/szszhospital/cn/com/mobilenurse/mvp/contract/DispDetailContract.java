package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

public interface DispDetailContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
    }
}
