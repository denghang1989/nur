package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public interface HerbalOrderContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showOrderList(List<Order> list);

        void refresh();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getZOrderList(String OrderType, String EpisodeID);
    }
}
