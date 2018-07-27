package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityScanQrcodeBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;

/**
 * 扫描二维码
 */
public class ScanQRCodeActivity extends BaseActivity<ActivityScanQrcodeBinding> implements QRCodeView.Delegate {
    private static final String TAG = "ScanQRCodeActivity";

    public static final int RESULT_CODE = 100;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scan_qrcode;
    }

    @Override
    protected void init() {
        super.init();
        mDataBinding.zxingview.setDelegate(this);
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDataBinding.zxingview.startCamera();
        mDataBinding.zxingview.showScanRect();
    }

    @Override
    protected void onStop() {
        mDataBinding.zxingview.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mDataBinding.zxingview.onDestroy();
        super.onDestroy();
    }

    public static void startScanQRCodeActivityForResult(Activity context) {
        context.startActivityForResult(new Intent(context, ScanQRCodeActivity.class), RESULT_CODE);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        mDataBinding.zxingview.startSpot();
        EventBus.getDefault().post(new QRCodeEvent(result));
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.d(TAG, "onScanQRCodeOpenCameraError: ");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

}
