package szszhospital.cn.com.mobilenurse.mvp.contract;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.BaseModel;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.entity.PacsImagePath;

public interface BrowsePacsImageContract {

    interface View extends BaseView {

        void showProgress();

        void hideProgress();

        void savePacsImagePath(List<PacsImagePath> pacsImagePaths);
    }

    interface Model extends BaseModel {

    }

    interface Presenter extends BasePresenter<View> {
        void getPacsImagePath(String studyId, String locId, String ordItemId);
    }

}
