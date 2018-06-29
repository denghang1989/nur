package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.design.widget.TabLayout;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainFragmentAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentMainBinding;
import szszhospital.cn.com.mobilenurse.entity.ModuleTable;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LocAccessPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.LocAccessRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.utils.Contants;

public class MainFragment extends BasePresenterFragment<FragmentMainBinding, LocAccessPresenter> implements LocAccessContract.View {

    private String           mLocId;
    private LocAccessRequest mRequest;

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
        mLocId = App.loginUser.UserLoc;
        mRequest = new LocAccessRequest();
        mRequest.LocId = App.loginUser.UserLoc;
    }

    @Override
    protected void initView() {
        mDataBinding.workList.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDataBinding.tabLayout));
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.workList);
    }

    @Override
    protected void initData() {
        mPresenter.getLocAccess(mRequest);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected LocAccessPresenter initPresenter() {
        return new LocAccessPresenter();
    }

    private static final String TAG = "MainFragment";

    @Override
    public void setPageAdapter(List<LocAccessResponse> list) {
        List<ModuleTable> moduleTableList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LocAccessResponse response = list.get(i);
            ModuleTable table = new ModuleTable();
            table.className = response.Model;
            table.isActivity = true;
            table.moduleId = i;
            table.moduleDes = response.Title;
            moduleTableList.add(table);
        }
        if (moduleTableList.size() == 0) {
            moduleTableList = Contants.getModuleList(mLocId, _mActivity);
        }
        MainFragmentAdapter adapter = new MainFragmentAdapter(getChildFragmentManager(), moduleTableList);
        mDataBinding.workList.setAdapter(adapter);
    }
}
