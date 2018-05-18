package szszhospital.cn.com.mobilenurse.mvp.presenter;

import android.util.Log;

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
                        Log.d(TAG, "onNext: ");
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

    }

    @Override
    public void getSingleDrugInfo(SingleDrugInfoRequest request) {

    }
}
