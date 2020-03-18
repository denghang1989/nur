package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;

public interface EposideVitalSignsContract {
    interface View extends BaseView {

        void showOrderList(List<String> list);

        void refresh();

        void showEmptyData();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getImageList(String EpisodeID);
    }
}