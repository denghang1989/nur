package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LisOrderModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public class LisOrderPresenter extends RxPresenter<LisOrderContract.View, LisOrderContract.Model> implements LisOrderContract.Presenter {

    private static final String TAG = "LisOrderPresenter";

    public LisOrderPresenter() {
        mModel = new LisOrderModel();
    }

    @Override
    public void getLisOrderList(String PatientID, String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientLisOrder(PatientID, EpisodeID)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .flatMap((Function<List<LisOrder>, ObservableSource<LisOrder>>) lisOrders -> Observable.fromIterable(lisOrders))
                .filter(lisOrder -> StringUtils.equals(lisOrder.ResultStatus, "3"))
                .toList()
                .subscribe(new SingleObserver<List<LisOrder>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onSuccess(List<LisOrder> orders) {
                        Collections.sort(orders, new Comparator<LisOrder>() {
                            @Override
                            public int compare(LisOrder o1, LisOrder o2) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                long o1Long = TimeUtils.string2Millis(o1.AuthDateTime, simpleDateFormat);
                                long o2Long = TimeUtils.string2Millis(o2.AuthDateTime, simpleDateFormat);
                                if (o2Long - o1Long >= 0) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            }
                        });
                        mView.showLisOrderList(orders);
                        mModel.save(orders);
                        mView.hideProgress();
                        mView.refresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.refresh();
                    }
                });
    }
}
