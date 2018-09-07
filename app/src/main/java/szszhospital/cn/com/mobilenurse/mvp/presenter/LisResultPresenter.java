package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisResultContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisResultPresenter extends RxPresenter<LisResultContract.View, LisResultContract.Model> implements LisResultContract.Presenter {

    @Override
    public void getLisOrderListDetail(String ReportDRs) {
        mView.showProgress();
        ApiService.Instance().getService().getLisOrderDetail(ReportDRs)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<LisOrderDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<LisOrderDetail> lisOrderDetails) {
                        mView.showLisOrderListDetail(lisOrderDetails);
                        mView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }
}
