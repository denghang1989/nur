package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

public class LocAccessPresenter extends RxPresenter<LocAccessContract.View, LocAccessContract.Model, List<LocAccess>> implements LocAccessContract.Presenter {

    @Override
    public void getLocAccess(String LocId) {
        mView.showProgress();
        ApiService.Instance().getService().getLocAccess(LocId)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(this);

    }

    @Override
    public void onNext(List<LocAccess> locAccesses) {
        mView.setPageAdapter(locAccesses);
    }
}
