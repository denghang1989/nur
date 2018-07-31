package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LisOrderModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public class LisOrderPresenter extends RxPresenter<LisOrderContract.View, LisOrderContract.Model> implements LisOrderContract.Presenter {

    public LisOrderPresenter() {
        mModel = new LisOrderModel();
    }

    @Override
    public void getLisOrderList(String PatientID, String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientLisOrder(PatientID, EpisodeID)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<LisOrder>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LisOrder> orders) {
                        mView.showLisOrderList(orders);
                        mModel.save(orders);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                        mView.refresh();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                        mView.refresh();
                    }
                });
    }
}
