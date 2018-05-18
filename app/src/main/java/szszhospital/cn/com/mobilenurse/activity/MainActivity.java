package szszhospital.cn.com.mobilenurse.activity;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActiviyMainBinding;

public class MainActivity extends BaseActivity<ActiviyMainBinding> {

    private MainActivityAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activiy_main;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
        mDataBinding.viewPager.setAdapter(mAdapter);
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new MainActivityAdapter(getSupportFragmentManager());
    }
}
