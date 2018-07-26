package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

public interface EMRImageContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showEMRList(List<EMRImageInfo> list);

        void refresh();
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getEMRImageInfoList(String eposideId, String InternalID);
    }

}
