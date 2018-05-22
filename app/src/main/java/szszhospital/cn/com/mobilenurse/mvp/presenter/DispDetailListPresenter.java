package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.DispDetailListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

public class DispDetailListPresenter extends RxPresenter<DispDetailListContract.View, DispDetailListContract.Model> implements DispDetailListContract.Presenter {

    @Override
    public void getDispDetailList(DispDetailListRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getDispDetailList(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<DispDetailResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<DispDetailResponse> dispDetailResponses) {
                        mView.setDispDetailList(dispDetailResponses);
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
