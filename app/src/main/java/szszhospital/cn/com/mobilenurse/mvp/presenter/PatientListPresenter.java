package szszhospital.cn.com.mobilenurse.mvp.presenter;


import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PatientListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class PatientListPresenter extends RxPresenter<PatientListContract.View, PatientListContract.Model,List<PatientInfo>> implements PatientListContract.Presenter {

    @Override
    public void getPatientList(String userId, String LocID) {
        ApiService.Instance().getService().getPatientListByLocId(userId, LocID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .flatMap((Function<List<PatientInfo>, ObservableSource<PatientInfo>>) Observable::fromIterable)
                .map(patientInfo -> {
                    if (patientInfo.DisBed == null) {
                        patientInfo.DisBed = "无";
                    }
                    return patientInfo;
                })
                .sorted((o1, o2) -> o1.DisBed.compareTo(o2.DisBed))
                .toList()
                .toObservable()
                .subscribe(this);
    }

    @Override
    public void onNext(List<PatientInfo> patientInfos) {
        mView.showPatientList(patientInfos);
    }
}
