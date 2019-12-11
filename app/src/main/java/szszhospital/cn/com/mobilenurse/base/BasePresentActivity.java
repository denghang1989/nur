package szszhospital.cn.com.mobilenurse.base;

import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class BasePresentActivity<T extends ViewDataBinding, P extends BasePresenter> extends BaseActivity<T> implements BaseView {

    protected P mPresenter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected P initPresenter() {
        Object newInstance = null;
        TypeVariable<? extends Class<?>>[] parameters = this.getClass().getSuperclass().getTypeParameters();
        if (parameters.length > 1) {
            TypeVariable<? extends Class<?>> parameter = parameters[1];
            if (parameter instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) parameter).getActualTypeArguments();
                Class clazz = (Class) types[0];
                try {
                    newInstance = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return (P) newInstance;
    }

    @CallSuper
    @Override
    protected void init() {
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

}
