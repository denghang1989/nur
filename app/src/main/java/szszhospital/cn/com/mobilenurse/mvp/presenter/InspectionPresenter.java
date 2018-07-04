package szszhospital.cn.com.mobilenurse.mvp.presenter;

import android.util.Log;

import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.request.PacsOrderSubscribeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.HandlerInspectionLog;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionPresenter extends RxPresenter<InspectionContract.View, InspectionContract.Model> implements InspectionContract.Presenter {
    private static final String TAG = "InspectionPresenter";

    @Override
    public void getPacsOrderList(PacsOrderSubscribeRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientPacsSubscribe(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<PacsOrderSubscribe>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PacsOrderSubscribe pacsOrderSubscribe) {
                        List<PacsOrderSubscribe.OrderSubscribe> list = Stream.of(pacsOrderSubscribe.rows).filter(value -> !StringUtils.equals(value.StatusCode, "执行")).toList();
                        mView.showPacsOrderList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

    @Override
    public void saveOrUpdateLog(InspectionRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().saveOrUpdateLog(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<HandlerInspectionLog>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HandlerInspectionLog handlerInspectionLog) {
                        Log.d(TAG, "onNext: ");
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
