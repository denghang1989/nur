package szszhospital.cn.com.mobilenurse.activity;

import android.Manifest;
import android.content.Intent;
import android.view.Gravity;

import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActiviyMainBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;

public class MainActivity extends BaseActivity<ActiviyMainBinding> {
    public static final  int      RESULT_CODE = 100;
    private static final String[] perms       = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
    private MainActivityAdapter mAdapter;
    private RxPermissions       mRxPermissions;
    private Disposable          mDisposable;

    @Override
    protected int getLayoutId() {
        return R.layout.activiy_main;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.toolbar.setTitle(App.loginUser.UserName + "，您好！");
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new MainActivityAdapter(getSupportFragmentManager());
        mRxPermissions = new RxPermissions(this);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.scanQr.setOnClickListener(v -> {
            if (PermissionUtils.isGranted(perms)) {
                startScanQRCodeActivity();
            } else {
                if (mDisposable != null && !mDisposable.isDisposed()) {
                    mDisposable.dispose();
                }
                mDisposable = mRxPermissions.request(perms).subscribe(aBoolean -> {
                    if (aBoolean) {
                        startScanQRCodeActivity();
                    } else {
                        ToastUtils.showShort("需要相机权限");
                    }
                });
            }
        });

        mDataBinding.toolbar.setNavigationOnClickListener(v -> mDataBinding.drawerLayout.openDrawer(Gravity.START));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void startScanQRCodeActivity() {
        ScanQRCodeActivity.startScanQRCodeActivityForResult(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE) {
            String code = data.getStringExtra("code");
            ToastUtils.showShort(code);
            EventBus.getDefault().post(new QRCodeEvent(code));
        }
    }
}
