package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.LogBookRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfo;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfoRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LogBook;

public interface LogBookContract {

    interface View extends BaseView {
        void showLocList(List<LogBook> list);

        void showLoading();

        void hideLoading();

        void showLocInfo(LocInfo locInfo);
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getLog(LogBookRequest request);

        void getLocInfo(LocInfoRequest request);
    }
}
