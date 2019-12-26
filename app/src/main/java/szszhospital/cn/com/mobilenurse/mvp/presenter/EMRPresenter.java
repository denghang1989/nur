package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.BaseResponse;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

public class EMRPresenter extends RxPresenter<EMRContract.View, EMRContract.Model> implements EMRContract.Presenter {

    @Override
    public void getEMREposideList(String eposideId) {
        mView.showProgress();
        ApiService.Instance().getService().getEMREposideList(eposideId)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<EMREposideInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<EMREposideInfo> emrEposideLists) {
                        mView.showMenuList(emrEposideLists);
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

    @Override
    public void getEMRImagePath(String name) {
        ApiService.Instance().getService().getAndroidSysOptionValue(name)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<BaseResponse<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(BaseResponse<String> stringBaseResponse) {
                        String data = stringBaseResponse.data;
                        SPUtils.getInstance().put(name,data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
