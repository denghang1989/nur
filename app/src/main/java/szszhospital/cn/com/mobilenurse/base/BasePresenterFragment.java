package szszhospital.cn.com.mobilenurse.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;

public abstract class BasePresenterFragment<T extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<T> implements BaseView {

    protected T mDataBinding;
    protected P mPresenter;

    @CallSuper
    @Override
    protected void init() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private P initPresenter() {
        return null;
    }
}
