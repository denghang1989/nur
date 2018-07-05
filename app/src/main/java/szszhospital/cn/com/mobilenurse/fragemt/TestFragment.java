package szszhospital.cn.com.mobilenurse.fragemt;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentTestBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.TestContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.TestPresenter;

/**
 * 检验标本运送时间抽
 */
public class TestFragment extends BasePresenterFragment<FragmentTestBinding, TestPresenter> implements TestContract.View {
    private static final String TAG = "TestFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCode(QRCodeEvent event) {
        String code = event.code;
        Log.d(TAG, "handlerCode: " + code);
    }

}
