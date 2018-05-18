package szszhospital.cn.com.mobilenurse.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.util.Log;

/**
 * @param <T>
 * @desc 扫描二维码
 */
public abstract class BaseScanFragment<T extends ViewDataBinding> extends BaseFragment<T> {
    private static final String TAG        = "BaseScanFragment";
    private static final String KEY_ACTION = "android.intent.action.BARCODEDATA";
    private static final String KEY_RESULT = "barcode_result";
    public QRCodeBroadcastReceiver mBroadcastReceiver;

    @Override
    protected void init() {
        mBroadcastReceiver = new QRCodeBroadcastReceiver();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+mBroadcastReceiver);
        _mActivity.registerReceiver(mBroadcastReceiver, new IntentFilter(KEY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+mBroadcastReceiver);
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
