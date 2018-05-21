package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

public interface DrugBillSendContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setDrugBillList();

    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<DrugBillSendContract.View> {
        void getDrugBillList();
    }
}
