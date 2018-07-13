package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

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
                .subscribe(new Observer<List<PacsOrderSubscribe>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PacsOrderSubscribe> pacsOrderSubscribes) {
                        List<PacsOrderSubscribe> list = Stream.of(pacsOrderSubscribes).filter(value -> StringUtils.equals(value.repEmgFlag, "否")).toList();
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
                        mView.refresh();
                        switch (request.Status) {
                            case "A":
                                ToastUtils.showShort("病人离开病区，前往检查！");
                                break;
                            case "B":
                                ToastUtils.showShort("到达检查科室");
                                break;
                            case "C":
                                ToastUtils.showShort("病人回到病区！");
                                break;
                        }
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
