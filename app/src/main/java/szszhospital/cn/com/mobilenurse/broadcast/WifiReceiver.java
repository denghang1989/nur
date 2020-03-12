package szszhospital.cn.com.mobilenurse.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.StringUtils;

import org.greenrobot.eventbus.EventBus;

import szszhospital.cn.com.mobilenurse.event.WifiChangedEvent;

public class WifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (StringUtils.equals(action, WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            EventBus.getDefault().post(new WifiChangedEvent(NetworkUtils.isWifiConnected()));
        }
    }

}
