package szszhospital.cn.com.mobilenurse.mvp.presenter;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.AuditDetailRequest;
import szszhospital.cn.com.mobilenurse.remote.request.RobotDrugRequest;
import szszhospital.cn.com.mobilenurse.remote.response.AuditDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.RobotDrugResponse;

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
                        Log.d(TAG, "onNext: " + auditDetailResponses);
                        if (auditDetailResponses != null && auditDetailResponses.size() > 0) {
                            AuditDetailResponse response = auditDetailResponses.get(0);
                            if (response.RetCode == 1) {
                                //整单配药完成
                                ToastUtils.showShort("当前发药单，配药完成！");
                                mView.dispComplete();
                            } else if (response.RetCode == 0) {
                                //单个药品配药成功
                                dispDetailResponse.ConFirmFlag = "A";
                                mView.refresh();
                                ToastUtils.showShort("配药成功");
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

    @Override
    public void updateRobotDrugState(RobotDrugRequest robotDrugRequest) {
        mView.showProgress();
        ApiService.Instance().getService().updateRobotDrugState(obj2Map(robotDrugRequest))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<RobotDrugResponse>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   addSubscribe(d);
                               }

                               @Override
                               public void onNext(RobotDrugResponse robotDrugResponse) {
                                   if (robotDrugResponse != null) {
                                       if (StringUtils.equals("0", robotDrugResponse.RetCode)) {
                                           mView.refreshRobot(robotDrugResponse);
                                       }
                                   }
                               }

                               @Override
                               public void onError(Throwable e) {
                                   mView.hideProgress();
                                   e.printStackTrace();
                               }

                               @Override
                               public void onComplete() {
                                   mView.hideProgress();
                               }
                           }
                );
    }
}
