package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRImageContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

public class EMRImagePresenter extends RxPresenter<EMRImageContract.View, EMRImageContract.Model> implements EMRImageContract.Presenter {

    @Override
    public void getEMRImageInfoList(String eposideId, String InternalID) {
        mView.showProgress();
        ApiService.Instance().getService().getEMRImageList(eposideId, InternalID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<EMRImageInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<EMRImageInfo> emrImageInfos) {
                        mView.showEMRList(emrImageInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }
}
