package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsOrderItemContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;

public class PacsOrderItemPresenter extends RxPresenter<PacsOrderItemContract.View, PacsOrderItemContract.Model> implements PacsOrderItemContract.Presenter {

    @Override
    public void getPacsOrderList(String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getEposidePacsOrder(EpisodeID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<PacsOrderItem>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PacsOrderItem> pacsOrders) {
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
