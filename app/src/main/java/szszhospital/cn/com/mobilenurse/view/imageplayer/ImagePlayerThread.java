package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.os.HandlerThread;

public class ImagePlayerThread extends HandlerThread {

    public ImagePlayerThread() {
        this("ImagePlayer");
    }

    public ImagePlayerThread(String name) {
        super(name);
    }
}
