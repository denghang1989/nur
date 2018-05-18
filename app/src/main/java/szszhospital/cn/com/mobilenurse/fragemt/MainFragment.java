package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.design.widget.TabLayout;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainFragmentAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentMainBinding;
import szszhospital.cn.com.mobilenurse.utils.Contants;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private String mLocId;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        mLocId = App.loginUser.UserLoc;
    }

    @Override
    protected void initView() {
        mDataBinding.workList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDataBinding.tabLayout));
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.workList);
    }

    @Override
    protected void initData() {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getChildFragmentManager(), Contants.getModuleList(mLocId, _mActivity));
        mDataBinding.workList.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }
}
