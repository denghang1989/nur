package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.TestStep;

public interface TestStepContract {
    interface View extends BaseView {

        void showProgress();

        void hideProgress();

        void showListView(List<TestStep> testSteps);
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getTestStepDetail(String EpisodeID,String LabNo);
    }
}
