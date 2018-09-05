package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.mvp.model.LocAccessModel;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.LocAccessRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;

public class LocAccessPresenter extends RxPresenter<LocAccessContract.View, LocAccessContract.Model> implements LocAccessContract.Presenter {

    public LocAccessPresenter() {
        mModel = new LocAccessModel();
    }

    @Override
    public void getLocAccess(LocAccessRequest request) {
        ApiService.Instance().getService().getLocAccess(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<LocAccessResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<LocAccessResponse> locAccessResponses) {
                        mModel.save(locAccessResponses);
                        mView.setPageAdapter(locAccessResponses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
