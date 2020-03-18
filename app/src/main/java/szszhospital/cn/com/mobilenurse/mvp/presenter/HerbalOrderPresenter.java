package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.HerbalOrderContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

/**
 * @author Administrator
 */
public class HerbalOrderPresenter extends RxPresenter<HerbalOrderContract.View, HerbalOrderContract.Model,List<Order>> implements HerbalOrderContract.Presenter {

    @Override
    public void getZOrderList(String OrderType, String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService()
                .getPatientHerbalOrder(OrderType, EpisodeID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .flatMap((Function<List<Order>, ObservableSource<Order>>) Observable::fromIterable)
                .filter(order -> !(StringUtils.equals(order.OrdStatus, "撤销") || StringUtils.equals(order.OrdStatus, "作废")))
                .toSortedList(new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
                        Date o1Time = TimeUtils.string2Date(o1.OrdStartDate + " " + o1.OrdStartTime, format);
                        Date o2Time = TimeUtils.string2Date(o2.OrdStartDate + " " + o2.OrdStartTime, format);
                        return o1Time.compareTo(o2Time);
                    }
                })
                .toObservable()
                .subscribe(this);
    }

    @Override
    public void onNext(List<Order> orders) {
        if ((orders != null) && (orders.size() > 0)) {
            mView.showOrderList(orders);
        } else {
            mView.showEmptyData();
        }
    }

    @Override
    public void onComplete() {
        super.onComplete();
        mView.refresh();
    }
}
