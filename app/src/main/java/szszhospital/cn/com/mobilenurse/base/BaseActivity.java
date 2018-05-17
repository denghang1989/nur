package szszhospital.cn.com.mobilenurse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
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
}
