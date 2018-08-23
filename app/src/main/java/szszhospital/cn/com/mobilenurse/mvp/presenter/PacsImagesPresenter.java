package szszhospital.cn.com.mobilenurse.mvp.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;

public class PacsImagesPresenter extends RxPresenter<PacsImagesContract.View,PacsImagesContract.Model> implements PacsImagesContract.Presenter {
    private static final String TAG = "PacsImagesPresenter";

    @Override
    public void getPacsImages(String studyId, String type) {
        mView.showProgress();
        ApiService.Instance().getService()
                .getPacsImageFtpPath(studyId, type)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<PacsImagePath>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<PacsImagePath> pacsImagePaths) {
                        if (pacsImagePaths!=null && pacsImagePaths.size()>0) {
                            Log.d(TAG, "onNext: "+pacsImagePaths.toString());
                            mView.getRealImagePath(pacsImagePaths);
                        }
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
