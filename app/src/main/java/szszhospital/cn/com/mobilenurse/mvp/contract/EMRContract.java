package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

public interface EMRContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showMenuList(List<EMREposideInfo> list);

        void refresh();

        void showEMRList(List<EMRImageInfo> list);

    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getEMREposideList(String  eposideId);

        void getEMRImageInfoList(String eposideId, String InternalID);

        void getEMRImagePath(String name);
    }
}
