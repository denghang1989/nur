package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.request.DispDetailListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

public interface DispDetailListContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void setDispDetailList(List<DispDetailResponse> list);

    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        // 发药单
        void getDispDetailList(DispDetailListRequest request);
    }
}
