package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.LocAccessRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;

public interface LocAccessContract {

    interface View extends BaseView {
        void setPageAdapter(List<LocAccessResponse> list);
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getLocAccess(LocAccessRequest request);
    }

}
