package szszhospital.cn.com.mobilenurse.view.imageplayer;

public interface OnImagePlayerChanged {
    void onCreate();

    void update(int frameIndex);

    void onDestroyed();
}
