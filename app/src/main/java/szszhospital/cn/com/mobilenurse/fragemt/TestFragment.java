package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.annimon.stream.IntPair;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.TestAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentTestBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.TestContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.TestPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.Test;

/**
 * 检验标本运送时间抽
 */
public class TestFragment extends BasePresenterFragment<FragmentTestBinding, TestPresenter> implements TestContract.View {
    private static final String TAG = "TestFragment";
    private TestAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void showProgress() {
        mDataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showListView(Test test) {
        Optional<IntPair<Test>> optional = Stream.of(mAdapter.getData()).findIndexed((index, value) -> StringUtils.equals(value.LabNo, test.LabNo));
        if (!optional.isPresent()) {
            mAdapter.addData(test);
            mDataBinding.listView.scrollToPosition(mAdapter.getData().size() - 1);
        }
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new TestAdapter(R.layout.item_test);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.listView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.listView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                return false;
            }
        });
    }

    @Override
    protected TestPresenter initPresenter() {
        return new TestPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param event
     * @desc: 通过标本号获取标本信息，病人基本信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCode(QRCodeEvent event) {
        if (isSupportVisible()) {
            String code = event.code;
            if (!StringUtils.isTrimEmpty(code) && TextUtils.isDigitsOnly(code)) {
                mPresenter.getLisNoInfo(code, App.loginUser.UserDR);
            }
        }
    }

}
