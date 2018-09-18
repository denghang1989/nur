package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

public interface SearchContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showData();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void searchByNo(String number);
    }
}
