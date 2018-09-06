package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.OrderContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.OrderRequest;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class OrderPresenter extends RxPresenter<OrderContract.View, OrderContract.Model> implements OrderContract.Presenter {

    @Override
    public void getPatientOrderList(OrderRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientOrderList(obj2Map(request))
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        if (orders != null && orders.size() > 0) {
                            for (int i = 0; i < orders.size(); i++) {
                                Order order = orders.get(i);
                                if (order.SeqNo.contains(".")) {
                                    String number = order.SeqNo.split("\\.")[0];
                                    order.SeqNo = number;
                                }
                            }
                            Collections.reverse(orders);
                            mView.showPatientOrderList(orders);
                        }
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
