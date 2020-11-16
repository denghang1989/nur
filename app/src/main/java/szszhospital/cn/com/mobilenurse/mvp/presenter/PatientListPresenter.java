package szszhospital.cn.com.mobilenurse.mvp.presenter;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PatientListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class PatientListPresenter extends RxPresenter<PatientListContract.View, PatientListContract.Model,List<PatientInfo>> implements PatientListContract.Presenter {

    @Override
    public void getPatientList(String userId, String LocID,String IsLoc) {
        ApiService.Instance().getService().getPatientListByLocId(userId, LocID,IsLoc)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .flatMap((Function<List<PatientInfo>, ObservableSource<PatientInfo>>) Observable::fromIterable)
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
