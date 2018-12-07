package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.SearchContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class SearchPresenter extends RxPresenter<SearchContract.View,SearchContract.Model> implements SearchContract.Presenter {
    private static final String TAG = "SearchPresenter";

    @Override
    public void searchByNo(String number) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientListByNo(number)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<PatientInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<PatientInfo> patientInfos) {
                        if (patientInfos != null) {
                            Collections.sort(patientInfos);
                            mView.showData(patientInfos);
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
