package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LogBookContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.LogBookRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfoRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LogBook;

public class LogBookPresenter extends RxPresenter<LogBookContract.View,LogBookContract.Model> implements LogBookContract.Presenter {

    @Override
    public void getLog(LogBookRequest request) {
        mView.showLoading();
        ApiService.Instance().getService().getLogBookList(obj2Map(request))
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<LogBook>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<LogBook> logBooks) {
                        mView.showLocList(logBooks);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    @Override
    public void getLocInfo(LocInfoRequest request) {

    }
}
