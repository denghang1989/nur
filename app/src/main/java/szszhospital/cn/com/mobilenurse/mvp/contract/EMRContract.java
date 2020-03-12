package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.EMRNavigation;

public interface EMRContract {

    interface View extends BaseView {

        void showMenuList(List<EMRNavigation> list);

        void refresh();

        void showEMRList(List<String> list);

    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getEMREposideList(String  eposideId);

        void getEMRImageInfoList(String eposideId, String InternalID);

        void getEMRImagePath(String name);
    }
}
