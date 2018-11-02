package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.LogBookActivity;
import szszhospital.cn.com.mobilenurse.adapter.LogBookAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentLogBookBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.LogBookContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.LogBookPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.LogBookRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfo;
import szszhospital.cn.com.mobilenurse.remote.response.LogBook;

/**
 * 交班本
 */
public class LogBookFragment extends BasePresenterFragment<FragmentLogBookBinding,LogBookPresenter> implements LogBookContract.View {

    private LogBookAdapter mAdapter;
    private LogBookRequest mRequest;

    public static LogBookFragment newInstance() {
        return new LogBookFragment();
    }

    @Override
    protected void init() {
        super.init();
        setHasOptionsMenu(true);
        mAdapter = new LogBookAdapter(R.layout.item_log_book);
        mRequest = new LogBookRequest();
        mRequest.LocId = App.loginUser.UserLoc;
        mRequest.userId = App.loginUser.UserDR;
        mRequest.SumDate = TimeUtils.date2String(new Date(),new SimpleDateFormat("yyyy-MM-dd"));
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(_mActivity,DividerItemDecoration.VERTICAL));
        mDataBinding.recyclerView.setAdapter(mAdapter);
        ((LogBookActivity)_mActivity).setSupportActionBar(mDataBinding.toolbar);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getLog(mRequest);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> _mActivity.finish());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_log_book;
    }

    @Override
    protected LogBookPresenter initPresenter() {
        return new LogBookPresenter();
    }

    @Override
    public void showLocList(List<LogBook> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void showLoading() {
        mDataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mDataBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLocInfo(LocInfo locInfo) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logbook,menu);
    }


}
