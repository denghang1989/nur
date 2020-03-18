package szszhospital.cn.com.mobilenurse.mvp.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.MainContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.UpdateApp;

public class MainPresenter extends RxPresenter<MainContract.View, MainContract.Model,UpdateApp> implements MainContract.Presenter {

    @Override
    public void getUpdateApp() {
        mView.showProgress();
        ApiService.Instance().getService().getUpdateAppInfo()
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(this);
    }

    @Override
    public void onNext(UpdateApp updateApp) {
        mView.showDialog(updateApp);
    }
}
