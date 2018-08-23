package szszhospital.cn.com.mobilenurse.mvp.contract;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;

public interface PacsImagesContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showImages();

        void getRealImagePath(PacsImagePath pacsImagePath);
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPacsImages(String studyId, String type);
    }
}
