package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionStepContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.response.InspectionLogDetail;

public class InspectionStepPresenter extends RxPresenter<InspectionStepContract.View, InspectionStepContract.Model> implements InspectionStepContract.Presenter {

    @Override
    public void getPacsOrderDetail(InspectionRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getInspectionLogDetail(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<InspectionLogDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<InspectionLogDetail> inspectionLogDetails) {
                        mView.showDetails(inspectionLogDetails);
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
