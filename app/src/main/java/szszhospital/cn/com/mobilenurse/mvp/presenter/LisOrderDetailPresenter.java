package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderDetailContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisOrderDetailPresenter extends RxPresenter<LisOrderDetailContract.View, LisOrderDetailContract.Model> implements LisOrderDetailContract.Presenter {

    @Override
    public void getLisOrderListDetail(String ReportDRs) {
        ApiService.Instance().getService().getLisOrderDetail(ReportDRs)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<LisOrderDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LisOrderDetail> lisOrderDetails) {
                        mView.showLisOrderListDetail(lisOrderDetails);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
