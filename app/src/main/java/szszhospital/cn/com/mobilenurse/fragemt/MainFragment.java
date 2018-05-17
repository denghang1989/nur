package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainFragmentAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private MainFragmentAdapter mAdapter;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
