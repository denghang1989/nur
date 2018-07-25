package szszhospital.cn.com.mobilenurse.utils;

import android.os.HandlerThread;

public class AsynHandlerThread extends HandlerThread {

    public AsynHandlerThread(){
        this("AsynHandlerThread");
    }

    public AsynHandlerThread(String name) {
        super(name);
    }
}
