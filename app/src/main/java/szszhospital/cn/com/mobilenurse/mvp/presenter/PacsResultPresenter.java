package szszhospital.cn.com.mobilenurse.mvp.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsResultContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsResult;

public class PacsResultPresenter extends RxPresenter<PacsResultContract.View, PacsResultContract.Model> implements PacsResultContract.Presenter {

    @Override
    public void getPacsResult(String ItemOrderId) {
        mView.showProgress();
        ApiService.Instance().getService().getPacsResultByOrderId(ItemOrderId)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<PacsResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(PacsResult pacsResult) {
                        mView.showPacsResult(pacsResult);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }
}
