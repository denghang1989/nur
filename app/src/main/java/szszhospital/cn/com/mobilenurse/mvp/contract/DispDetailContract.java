package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.AuditDetailRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

public interface DispDetailContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void refresh();

        void dispComplete();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void updateDrugState(AuditDetailRequest request, DispDetailResponse dispDetailResponse);
    }
}
