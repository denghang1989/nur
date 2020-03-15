package szszhospital.cn.com.mobilenurse.base;

import com.chad.library.adapter.base.BaseQuickAdapter;

import androidx.databinding.ViewDataBinding;
import androidx.annotation.CallSuper;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    protected void initRecyclerView(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(baseQuickAdapter);
    }
}
