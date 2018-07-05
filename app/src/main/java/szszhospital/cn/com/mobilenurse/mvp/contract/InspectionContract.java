package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.request.PacsOrderSubscribeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public interface InspectionContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showPacsOrderList(List<PacsOrderSubscribe> list);

    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPacsOrderList(PacsOrderSubscribeRequest request);

        void saveOrUpdateLog(InspectionRequest request);
    }
}
