package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.OrderContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class OrderPresenter extends RxPresenter<OrderContract.View, OrderContract.Model,List<Order>> implements OrderContract.Presenter {

    @Override
    public void getPatientOrderList(String OrderType, String EposideId) {
        ApiService.Instance().getService().getPatientOrderList(OrderType, EposideId)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(this);
    }

    @Override
    public void onNext(List<Order> orders) {
        mView.showPatientOrderList(orders);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        mView.refresh();
    }
}
