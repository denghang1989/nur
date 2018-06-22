package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.PatientListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public interface PatientListContract {

    interface View extends BaseView {

        void showPatientList(List<PatientInfo> list);

    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getPatientList(PatientListRequest request);
    }

}
