package szszhospital.cn.com.mobilenurse.mvp.presenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.base.RxPresenter;
import szszhospital.cn.com.mobilenurse.mvp.contract.EposideVitalSignsContract;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.VitalSign;

/**
 * @author Administrator
 */
public class EposideVitalSignsPresenter extends RxPresenter<EposideVitalSignsContract.View, EposideVitalSignsContract.Model,List<String>> implements EposideVitalSignsContract.Presenter {

    @Override
    public void getImageList(String EpisodeID) {
        mView.showProgress();
        ApiService.Instance().getService()
                .getEposideVitalSignsImage(EpisodeID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .flatMap((Function<List<VitalSign>, ObservableSource<VitalSign>>) Observable::fromIterable)
                .map(vitalSign -> vitalSign.filePath)
                .toList()
                .toObservable()
                .subscribe(this);
    }

    @Override
    public void onNext(List<String> strings) {
        if (strings.size() > 0) {
            mView.showOrderList(strings);
        } else {
            mView.showEmptyData();
        }
    }
}
