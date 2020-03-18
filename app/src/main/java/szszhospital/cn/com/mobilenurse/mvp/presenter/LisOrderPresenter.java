package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public class LisOrderPresenter extends RxPresenter<LisOrderContract.View, LisOrderContract.Model, List<LisOrder>> implements LisOrderContract.Presenter {

    private static final String TAG = "LisOrderPresenter";

    public LisOrderPresenter() {
    }

    @Override
    public void getLisOrderList(String PatientID, String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientLisOrder(PatientID, EpisodeID)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .flatMap((Function<List<LisOrder>, ObservableSource<LisOrder>>) lisOrders -> Observable.fromIterable(lisOrders))
                .filter(lisOrder -> StringUtils.equals(lisOrder.ResultStatus, "3"))
                .toList().toObservable()
                .subscribe(this);
    }

    @Override
    public void onNext(List<LisOrder> orders) {
        mView.showLisOrderList(orders);
        mView.hideProgress();
        mView.refresh();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        mView.refresh();
    }
}
