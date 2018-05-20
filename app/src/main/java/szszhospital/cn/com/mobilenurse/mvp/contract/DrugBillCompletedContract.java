package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

/**
 * 2018/5/20 10
 */
public interface DrugBillCompletedContract {


    interface View extends BaseView {
    }

    interface Presenter<T extends BaseView> extends BasePresenter<T> {
    }

    interface Model extends BaseModel {
    }


}