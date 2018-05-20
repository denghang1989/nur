package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

/**
 * 2018/5/20 09
 */
public interface DrugBillUnCompletedContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showDrugBillList();
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getDrugBillList();
    }
}
