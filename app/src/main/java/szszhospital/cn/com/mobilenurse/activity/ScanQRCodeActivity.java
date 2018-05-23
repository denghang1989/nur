package szszhospital.cn.com.mobilenurse.activity;

import android.app.Activity;
import android.content.Intent;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityScanQrcodeBinding;

public class ScanQRCodeActivity extends BaseActivity<ActivityScanQrcodeBinding> implements QRCodeView.Delegate {

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
        setResult(RESULT_CODE, new Intent().putExtra("code", result));
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }
}
