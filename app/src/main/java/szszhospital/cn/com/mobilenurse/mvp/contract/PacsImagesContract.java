package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.entity.PacsImagePath;

public interface PacsImagesContract {
    interface View extends BaseView {

        void getRealImagePath(List<PacsImagePath> pacsImagePaths);

        void showNoData();

        void hideNoData();
    }

    interface Model extends BaseModel {
    }

    interface Presenter extends BasePresenter<View> {
        void getPacsImages(String studyId, String type);
    }
}
