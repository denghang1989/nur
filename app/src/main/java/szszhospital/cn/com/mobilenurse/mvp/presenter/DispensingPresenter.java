package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispensingContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillRequest;
import szszhospital.cn.com.mobilenurse.remote.request.PatientInfoRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SingleDrugInfoRequest;
import szszhospital.cn.com.mobilenurse.remote.response.Drug;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfoResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SingleDrugInfoResponse;

public class DispensingPresenter extends RxPresenter<DispensingContract.View, DispensingContract.Model> implements DispensingContract.Presenter {
    private static final String TAG = "DispensingPresenter";

    @Override
    public void getDispInfo(DrugBillRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getDispInfo(obj2Map(request))
                .compose(RxUtil.<Response<List<Drug>>>rxSchedulerHelper())
                .compose(RxUtil.<List<Drug>>httpHandleResponse())
                .subscribe(new Observer<List<Drug>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<Drug> drugs) {
                        mView.setDrugBillList(drugs);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.showProgress();
                    }
                });

    }

    @Override
    public void getPatientInfo(PatientInfoRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientInfo(obj2Map(request))
                .compose(RxUtil.<Response<PatientInfoResponse>>rxSchedulerHelper())
                .compose(RxUtil.<PatientInfoResponse>httpHandleResponse()).subscribe(new Observer<PatientInfoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                addSubscribe(d);
            }

            @Override
            public void onNext(PatientInfoResponse patientInfoResponse) {

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

    @Override
    public void getSingleDrugInfo(SingleDrugInfoRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().getSingleDrugInfo(obj2Map(request))
                .compose(RxUtil.<Response<SingleDrugInfoResponse>>rxSchedulerHelper())
                .compose(RxUtil.<SingleDrugInfoResponse>httpHandleResponse())
                .subscribe(new Observer<SingleDrugInfoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(SingleDrugInfoResponse singleDrugInfoResponse) {

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
