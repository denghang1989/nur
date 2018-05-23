package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillListRequest;
import szszhospital.cn.com.mobilenurse.remote.request.SaveAuditStatusRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;
import szszhospital.cn.com.mobilenurse.remote.response.SaveAuditStatusResponse;

/**
 * 2018/5/20 09
 */
public class DrugBillPresenter extends RxPresenter<DrugBillContract.View, DrugBillContract.Model> implements DrugBillContract.Presenter {

    @Override
    public void getDrugBillList(DrugBillListRequest request) {
        ApiService.Instance().getService().getDrugBillList(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<DrugBill>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<DrugBill> list) {
                        mView.hideProgress();
                        mView.showDrugBillList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }

    @Override
    public void saveAuditStatus(SaveAuditStatusRequest request) {
        mView.showProgress();
        ApiService.Instance().getService().saveAuditStatus(obj2Map(request))
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<SaveAuditStatusResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addSubscribe(d);
                    }

                    @Override
                    public void onNext(List<SaveAuditStatusResponse> saveAuditStatusResponses) {
                        if (saveAuditStatusResponses != null && saveAuditStatusResponses.size() > 0) {
                            SaveAuditStatusResponse response = saveAuditStatusResponses.get(0);
                            if (response.RetCode == 0) {
                                ToastUtils.showShort("核对成功");
                                mView.refresh();
                            } else {
                                ToastUtils.showShort("核对失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideProgress();
                    }
                });
    }
}
