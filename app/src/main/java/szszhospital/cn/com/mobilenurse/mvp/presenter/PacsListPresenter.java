package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsListContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsListPresenter extends RxPresenter<PacsListContract.View, PacsListContract.Model> implements PacsListContract.Presenter {
    private static final String TAG = "PacsListPresenter";

    @Override
    public void getPacsOrderList(String EpisodeID, String userCode) {
        mView.showProgress();
        ApiService.Instance().getService().getPatientPacsOrder(EpisodeID, userCode)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<PacsOrder>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PacsOrder> pacsOrders) {
                        Collections.sort(pacsOrders, (o1, o2) -> {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                            long o1Time = TimeUtils.string2Millis(o1.TItemDate, format);
                            long o2Time = TimeUtils.string2Millis(o2.TItemDate, format);
                            return (int) (o2Time - o1Time);
                        });
                        mView.showPacsOrderList(pacsOrders);
                        mView.refresh();
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
