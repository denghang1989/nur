package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.PacsOrderSubscribeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionPresenter extends RxPresenter<InspectionContract.View, InspectionContract.Model> implements InspectionContract.Presenter {

    @Override
    public void getPacsOrderList(PacsOrderSubscribeRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientPacsSubscribe(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<PacsOrderSubscribe>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PacsOrderSubscribe pacsOrderSubscribe) {
                        List<PacsOrderSubscribe.OrderSubscribe> orderSubscribes = pacsOrderSubscribe.rows;
                        mView.showPacsOrderList(orderSubscribes);
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
