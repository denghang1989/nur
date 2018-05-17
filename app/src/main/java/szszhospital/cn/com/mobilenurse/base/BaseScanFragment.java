package szszhospital.cn.com.mobilenurse.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;

/**
 * @param <T>
 * @desc 扫描二维码
 */
public abstract class BaseScanFragment extends BaseFragment {

    private static final String KEY_ACTION = "android.intent.action.BARCODEDATA";
    private static final String KEY_RESULT = "barcode_result";
    private QRCodeBroadcastReceiver mBroadcastReceiver;
    private IntentFilter            mIntentFilter;

    @Override
    protected void init() {
        super.init();
        mBroadcastReceiver = new QRCodeBroadcastReceiver();
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(KEY_ACTION);
    }

    @Override
    public void onResume() {
        super.onResume();
        _mActivity.registerReceiver(mBroadcastReceiver, mIntentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        _mActivity.unregisterReceiver(mBroadcastReceiver);
    }

    private class QRCodeBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, KEY_ACTION)) {
                String code = intent.getStringExtra(KEY_RESULT);
            }
        }
    }
}
