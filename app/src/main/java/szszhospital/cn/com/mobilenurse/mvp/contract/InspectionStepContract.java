package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.response.InspectionLogDetail;

public interface InspectionStepContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showDetails(List<InspectionLogDetail> inspectionLogDetails);
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPacsOrderDetail(InspectionRequest request);
    }
}
