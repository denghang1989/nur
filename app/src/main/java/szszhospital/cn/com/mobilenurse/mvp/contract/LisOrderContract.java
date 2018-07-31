package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public interface LisOrderContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showLisOrderList(List<LisOrder> list);

        void refresh();
    }

    interface Model extends BaseModel {
        void save(List<LisOrder> list);
    }

    interface Presenter extends BasePresenter<View> {
        void getLisOrderList(String PatientID, String EpisodeID);
    }
}
