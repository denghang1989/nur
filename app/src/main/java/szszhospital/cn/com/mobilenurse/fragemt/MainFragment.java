package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.design.widget.TabLayout;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainFragmentAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentMainBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LocAccessPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.LocAccessRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse_Table;
import szszhospital.cn.com.mobilenurse.dialog.TitleSheetDialogFragment;

/**
 * 主界面
 */
public class MainFragment extends BasePresenterFragment<FragmentMainBinding, LocAccessPresenter> implements LocAccessContract.View {

    private static final String TAG = "MainFragment";
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
        mDataBinding.potion.setOnClickListener(v -> {
            int[] positions = new int[2];
            mDataBinding.potion.getLocationOnScreen(positions);
            TitleSheetDialogFragment fragment = TitleSheetDialogFragment.newInstance(positions);
            fragment.show(getChildFragmentManager(), "title");
        });
    }

    @Override
    protected LocAccessPresenter initPresenter() {
        return new LocAccessPresenter();
    }

    @Override
    public void setPageAdapter(List<LocAccessResponse> list) {
        List<LocAccessResponse> locAccess = new Select().from(LocAccessResponse.class).where(LocAccessResponse_Table.LocId.eq(App.loginUser.UserLoc)).orderBy(LocAccessResponse_Table.position,true).queryList();
        if (locAccess.size() == 0) {
            MainFragmentAdapter adapter = new MainFragmentAdapter(getChildFragmentManager(), list);
            mDataBinding.workList.setAdapter(adapter);
        } else if (list.size() == locAccess.size()) {
            MainFragmentAdapter adapter = new MainFragmentAdapter(getChildFragmentManager(), locAccess);
            mDataBinding.workList.setAdapter(adapter);
        }

    }
}
