package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PatientListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.PatientListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class PatientListPresenter extends RxPresenter<PatientListContract.View, PatientListContract.Model> implements PatientListContract.Presenter {

    @Override
    public void getPatientList(PatientListRequest request) {
        ApiService.Instance().getService().getPatientListByLocId(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<PatientInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<PatientInfo> patientInfos) {
                        List<PatientInfo> list = Stream.of(patientInfos).map(patientInfo -> {
                            if (patientInfo.DisBed == null) {
                                patientInfo.DisBed = "无";
                            }
                            return patientInfo;
                        }).sortBy(patientInfo -> patientInfo.DisBed).collect(Collectors.toList());
                        mView.showPatientList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.refresh();
                    }

                    @Override
                    public void onComplete() {
                        mView.refresh();
                    }
                });
    }
}
