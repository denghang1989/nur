package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.OrderRequest;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public interface OrderContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showPatientOrderList(List<Order> list);

        void refresh();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPatientOrderList(OrderRequest request);
    }
}
