package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 09
 */
public interface DrugBillContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showDrugBillList(List<DrugBill> list);

    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getDrugBillList(DrugBillListRequest request);
    }
}
