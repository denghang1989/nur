package szszhospital.cn.com.mobilenurse.activity;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private MainActivityAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activiy_main;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
    }

    @Override
    protected void init() {
        //mAdapter = new MainActivityAdapter(getSupportFragmentManager());
    }
}
