package szszhospital.cn.com.mobilenurse.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;

public abstract class BasePresentActivity<T extends ViewDataBinding, P extends BasePresenter> extends BaseActivity<T> implements BaseView {

    protected T mDataBinding;
    protected P mPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected P initPresenter() {
        return null;
    }

    @CallSuper
    protected void init() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

}
