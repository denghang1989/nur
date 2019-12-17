package szszhospital.cn.com.mobilenurse.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BasePresenterFragment<T extends ViewDataBinding, P extends BasePresenter> extends BaseFragment<T> implements BaseView {

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

    protected P initPresenter() {
        Object newInstance = null;
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            Class clazz = (Class) types[1];
            try {
                newInstance = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (P) newInstance;
    }
}
