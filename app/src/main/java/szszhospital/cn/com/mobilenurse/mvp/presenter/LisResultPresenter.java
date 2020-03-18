package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.LisResultContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisResultPresenter extends RxPresenter<LisResultContract.View, LisResultContract.Model,List<LisOrderDetail>> implements LisResultContract.Presenter {

    @Override
    public void getLisOrderListDetail(String ReportDRs) {
        mView.showProgress();
        ApiService.Instance().getService().getLisOrderDetail(ReportDRs)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(this);
    }

    @Override
    public void onNext(List<LisOrderDetail> lisOrderDetails) {
        mView.showLisOrderListDetail(lisOrderDetails);
        mView.hideProgress();
    }
}
