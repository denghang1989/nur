package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.os.Handler;
import android.os.Looper;

public class SchedulerUtil {
    private volatile static  Handler mAsynHandler;
    private volatile static Handler  mMainHandler;

    private SchedulerUtil(){

    }

    public static Handler getAsynHandler(){
        if (mAsynHandler == null) {
            synchronized (SchedulerUtil.class) {
                if (mAsynHandler == null) {
                    ImagePlayerThread imagePlayerThread = new ImagePlayerThread();
                    imagePlayerThread.start();
                    Looper looper = imagePlayerThread.getLooper();
                    mAsynHandler = new Handler(looper);
                }
            }
        }
        return mAsynHandler;
    }

    public static Handler getMainHandler(){
        if (mMainHandler == null) {
            synchronized (SchedulerUtil.class) {
                if (mMainHandler == null) {
                    mAsynHandler = new Handler();
                }
            }
        }
        return mMainHandler;
    }
}
