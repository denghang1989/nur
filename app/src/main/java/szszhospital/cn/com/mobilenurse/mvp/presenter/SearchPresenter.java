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

public class SearchPresenter extends RxPresenter<SearchContract.View, SearchContract.Model, List<PatientInfo>> implements SearchContract.Presenter {
    private static final String TAG = "SearchPresenter";

    @Override
    public void searchByNo(String number) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientListByNo(number)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(this);
    }

    @Override
    public void onNext(List<PatientInfo> patientInfos) {
        if ((patientInfos != null) && (patientInfos.size() > 0)) {
            Collections.sort(patientInfos);
            mView.showData(patientInfos);
        } else {

        }
    }
}
