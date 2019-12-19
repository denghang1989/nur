package szszhospital.cn.com.mobilenurse.view.imageplayer;

import szszhospital.cn.com.mobilenurse.utils.AsynHandlerThread;

public class ImagePlayerThread extends AsynHandlerThread {

    public ImagePlayerThread() {
        this("ImagePlayer");
    }

    public ImagePlayerThread(String name) {
        super(name);
    }
}
