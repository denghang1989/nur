package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.PacsResult;

public interface PacsResultContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showPacsResult(PacsResult pacsResult);
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPacsResult(String ItemOrderId);
    }
}
