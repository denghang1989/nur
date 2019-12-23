package szszhospital.cn.com.mobilenurse.base;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 2016/10/24 18
 */
public class RxPresenter<T extends BaseView, E extends BaseModel> implements BasePresenter<T> {
    protected T                   mView;
    protected E                   mModel;
    private   CompositeDisposable mCompositeSubscription;
    private static final String TAG = "RxPresenter";

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
}
