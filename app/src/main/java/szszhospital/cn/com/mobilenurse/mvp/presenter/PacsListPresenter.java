package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.blankj.utilcode.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        HashMap<String, List<PacsOrder>> map = new HashMap<>();
                        for (int i = 0; i < pacsOrders.size(); i++) {
                            PacsOrder pacsOrder = pacsOrders.get(i);
                            if (!StringUtils.isTrimEmpty(pacsOrder.TStudyNo)) {
                                if (!map.containsKey(pacsOrder.TStudyNo)) {
                                    List<PacsOrder> list = new ArrayList<>();
                                    list.add(pacsOrder);
                                    map.put(pacsOrder.TStudyNo, list);
                                } else {
                                    List<PacsOrder> list = map.get(pacsOrder.TStudyNo);
                                    list.add(pacsOrder);
                                }
                            }
                        }
                        Map<String, Long> mapData = new HashMap<>();
                        for (Map.Entry<String, List<PacsOrder>> entry : map.entrySet()) {
                            String key = entry.getKey();
                            List<PacsOrder> value = entry.getValue();
                            long count = Stream.of(value).distinctBy(new Function<PacsOrder, String>() {
                                @Override
                                public String apply(PacsOrder pacsOrder) {
                                    return pacsOrder.TreplocDr;
                                }
                            }).count();
                            mapData.put(key, count);
                        }

                        for (PacsOrder pacsOrder : pacsOrders) {
                            String key = pacsOrder.TStudyNo;
                            if (mapData.containsKey(key)) {
                                Long aLong = mapData.get(key);
                                if (aLong > 1) {
                                    pacsOrder.TUnRegister="Y";
                                }
                            }
                        }

                        // Log.d(TAG, "onNext: " + maps.toString());
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
