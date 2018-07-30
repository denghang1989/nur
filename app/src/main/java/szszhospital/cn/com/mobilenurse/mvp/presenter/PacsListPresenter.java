package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsListPresenter extends RxPresenter<PacsListContract.View,PacsListContract.Model> implements PacsListContract.Presenter{

    @Override
    public void getPacsOrderList(String EpisodeID, String userCode) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientPacsOrder(EpisodeID,userCode)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<PacsOrder>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PacsOrder> pacsOrders) {
                        mView.showPacsOrderList(pacsOrders);
                        mView.refresh();
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
