package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.HerbalOrderContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class HerbalOrderPresenter extends RxPresenter<HerbalOrderContract.View, HerbalOrderContract.Model> implements HerbalOrderContract.Presenter {

    @Override
    public void getZOrderList(String OrderType, String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientHerbalOrder(OrderType, EpisodeID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        List<Order> orderList = Stream.of(orders).filter(value -> !((StringUtils.equals(value.OrdStatus, "撤销") || StringUtils.equals(value.OrdStatus, "作废")))).sorted(new Comparator<Order>() {
                            @Override
                            public int compare(Order o1, Order o2) {
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                                Date o1Time = TimeUtils.string2Date(o1.OrdStartDate + " " + o1.OrdStartTime, format);
                                Date o2Time = TimeUtils.string2Date(o2.OrdStartDate + " " + o2.OrdStartTime, format);
                                return o1Time.compareTo(o2Time);
                            }
                        }).collect(Collectors.toList());
                        mView.showOrderList(orderList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                        mView.refresh();
                    }
                });
    }
}
