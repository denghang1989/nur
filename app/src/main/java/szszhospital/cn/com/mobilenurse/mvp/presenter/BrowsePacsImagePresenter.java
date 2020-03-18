package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.BrowsePacsImageContract;

public class BrowsePacsImagePresenter extends RxPresenter<BrowsePacsImageContract.View,BrowsePacsImageContract.Model, List<String>> implements BrowsePacsImageContract.Presenter {

    @Override
    public void getPacsImagePath(String studyId, String locId, String ordItemId) {

    }

    @Override
    public void onNext(List<String> strings) {

    }
}
