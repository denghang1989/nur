package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.AuditDetailRequest;
import szszhospital.cn.com.mobilenurse.remote.response.AuditDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

public class DispDetailPresenter extends RxPresenter<DispDetailContract.View, DispDetailContract.Model> implements DispDetailContract.Presenter {
    private static final String TAG = "DispDetailPresenter";

    @Override
    public void updateDrugState(AuditDetailRequest request, DispDetailResponse dispDetailResponse) {
        mView.showProgress();
        ApiService.Instance().getService().updateDrugState(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<AuditDetailResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<AuditDetailResponse> auditDetailResponses) {
                        if (auditDetailResponses != null && auditDetailResponses.size() > 0) {
                            AuditDetailResponse response = auditDetailResponses.get(0);
                            if (response.RetCode == 1) {
                                //整单配药完成
                                ToastUtils.showShort("当前发药单，配药完成！");
                            } else if (response.RetCode == 0) {
                                //单个药品配药成功
                                dispDetailResponse.ConFirmFlag = "A";
                                mView.refresh();
                            } else {
                                //单个药品配药失败
                                ToastUtils.showShort(response.RetDesc);
                            }
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
