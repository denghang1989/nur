package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;

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

                    }

                    @Override
                    public void onNext(List<EMREposideInfo> emrEposideLists) {
                        mView.showEMRList(emrEposideLists);
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
