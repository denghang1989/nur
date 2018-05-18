package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.db.LocTable;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillRequest;
import szszhospital.cn.com.mobilenurse.remote.request.PatientInfoRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SingleDrugInfoRequest;

public interface DispensingContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setSpinnerData(List<LocTable> list);

    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<DispensingContract.View> {
        // 发药单
        void getDispInfo(DrugBillRequest request);

        // 病人信息
        void getPatientInfo(PatientInfoRequest request);

        // 根据单个药品代码获取药品信息
        void getSingleDrugInfo(SingleDrugInfoRequest request);
    }
}
