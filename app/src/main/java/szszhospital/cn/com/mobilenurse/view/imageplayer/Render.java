package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.graphics.Bitmap;

public interface Render {
    void onDraw(int frameIndex, Bitmap bitmap);

    void onError(String message);

    void onClear();

}
