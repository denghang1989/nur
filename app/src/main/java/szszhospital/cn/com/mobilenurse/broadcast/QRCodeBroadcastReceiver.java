package szszhospital.cn.com.mobilenurse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;

public class QRCodeBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG        = "QRCodeBroadcastReceiver";
    private static final String KEY_ACTION = "android.intent.action.BARCODEDATA";
    private static final String KEY_RESULT = "barcode_result";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, KEY_ACTION)) {
            String code = intent.getStringExtra(KEY_RESULT);
            Log.d(TAG, "onReceive: " + code);
            EventBus.getDefault().post(new QRCodeEvent(code));
        }
    }
}
