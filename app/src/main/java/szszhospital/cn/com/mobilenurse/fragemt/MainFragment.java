package szszhospital.cn.com.mobilenurse.fragemt;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainFragmentAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentMainBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LocAccessPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

/**
 * 主界面
 */
public class MainFragment extends BasePresenterFragment<FragmentMainBinding, LocAccessPresenter> implements LocAccessContract.View {

    private static final String TAG = "MainFragment";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initView() {
        mDataBinding.workList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDataBinding.tabLayout));
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.workList);
    }

    @Override
    protected void initData() {
        mPresenter.getLocAccess(App.loginUser.UserLoc);
    }

    @Override
    protected LocAccessPresenter initPresenter() {
        return new LocAccessPresenter();
    }

    @Override
    public void setPageAdapter(List<LocAccess> list) {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getChildFragmentManager(), list);
        mDataBinding.workList.setAdapter(adapter);
    }
}
