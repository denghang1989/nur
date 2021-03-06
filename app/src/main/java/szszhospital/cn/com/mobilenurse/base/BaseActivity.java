package szszhospital.cn.com.mobilenurse.base;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends SwipeBackActivity {

    protected T mDataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_LEFT);
        init();
        initView();
        initData();
        initEvent();
    }

    protected void init() {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

    protected abstract int getLayoutId();

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation== Configuration.ORIENTATION_LANDSCAPE) {
            // 什么都不用写
        }
        else{
            // 什么都不用写
        }
    }
}
