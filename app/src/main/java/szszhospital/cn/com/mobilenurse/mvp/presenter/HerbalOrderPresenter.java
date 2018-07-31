package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.util.List;

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

                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        List<Order> orderList = Stream.of(orders).filter(value -> {
                            boolean result = true;
                            if (StringUtils.equals(value.OrdStatus, "撤销") || StringUtils.equals(value.OrdStatus, "作废")) {
                                result = false;
                            }
                            return result;
                        }).sorted((o1, o2) -> (int) TimeUtils.getTimeSpan((o1.OrdCreateDate + " " + o1.OrdCreateTime), (o2.OrdCreateDate + " " + o2.OrdCreateTime), TimeConstants.SEC)).collect(Collectors.toList());
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
