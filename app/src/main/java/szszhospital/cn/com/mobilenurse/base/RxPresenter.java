package szszhospital.cn.com.mobilenurse.base;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 2016/10/24 18
 */
public abstract class RxPresenter<T extends BaseView, E extends BaseModel, M> implements BasePresenter<T>, Observer<M> {
    protected            T                   mView;
    protected            E                   mModel;
    private              CompositeDisposable mCompositeSubscription;
    private static final String              TAG = "RxPresenter";

    public void addSubscribe(Disposable subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
            mCompositeSubscription.add(subscription);
        }
    }

    public void unSubscriber() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.dispose();
        }
    }

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        unSubscriber();
        mView = null;
    }

    @Override
    public void onSubscribe(Disposable d) {
        addSubscribe(d);
        mView.showProgress();
    }

    @Override
    public void onError(Throwable e) {
        mView.hideProgress();
    }

    @Override
    public void onComplete() {
        mView.hideProgress();
    }
}
