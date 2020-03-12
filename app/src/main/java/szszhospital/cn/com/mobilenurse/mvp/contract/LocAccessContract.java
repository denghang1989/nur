package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

public interface LocAccessContract {

    interface View extends BaseView {
        void setPageAdapter(List<LocAccess> list);
    }

    interface Model extends BaseModel {
        void save(List<LocAccess> list);

        List<LocAccess> getLocAccess(String LocId);
    }

    interface Presenter extends BasePresenter<View> {
        void getLocAccess(String LocId);
    }

}
