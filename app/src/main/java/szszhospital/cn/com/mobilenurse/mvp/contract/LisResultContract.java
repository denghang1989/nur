package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public interface LisResultContract {

    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showLisOrderListDetail(List<LisOrderDetail> list);

        void refresh();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getLisOrderListDetail(String ReportDRs);
    }
}
