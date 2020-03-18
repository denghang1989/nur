package szszhospital.cn.com.mobilenurse.mvp.presenter;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsOrderItemContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;

public class PacsOrderItemPresenter extends RxPresenter<PacsOrderItemContract.View, PacsOrderItemContract.Model,List<PacsOrderItem>> implements PacsOrderItemContract.Presenter {

    @Override
    public void getPacsOrderList(String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService().getEposidePacsOrder(EpisodeID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .flatMap((Function<List<PacsOrderItem>, ObservableSource<PacsOrderItem>>) Observable::fromIterable)
                .toSortedList((o1, o2) -> {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date o1Long = TimeUtils.string2Date(o1.DateTime, simpleDateFormat);
                    Date o2Long = TimeUtils.string2Date(o2.DateTime, simpleDateFormat);
                    return o2Long.compareTo(o1Long);
                }).toObservable()
                .subscribe(this);


    }

    @Override
    public void onNext(List<PacsOrderItem> pacsOrderItems) {
        mView.showPacsOrderList(pacsOrderItems);
        mView.refresh();
    }
}
