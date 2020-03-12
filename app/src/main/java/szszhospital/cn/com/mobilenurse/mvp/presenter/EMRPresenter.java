package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.BaseResponse;
import szszhospital.cn.com.mobilenurse.remote.response.EMRNavigation;

public class EMRPresenter extends RxPresenter<EMRContract.View, EMRContract.Model> implements EMRContract.Presenter {

    @Override
    public void getEMREposideList(String locId) {
        mView.showProgress();
        ApiService.Instance().getService().getEMRNavigation(locId)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .flatMap((Function<List<EMRNavigation>, ObservableSource<EMRNavigation>>) emrNavigations -> Observable.fromIterable(emrNavigations))
                .toSortedList((o1, o2) -> o1.ItemSeq - o2.ItemSeq)
                .toObservable()
                .subscribe(new Observer<List<EMRNavigation>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<EMRNavigation> emrEposideLists) {
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
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> emrImageInfos) {
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
                        SPUtils.getInstance().put(name, data);
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
