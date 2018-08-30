package szszhospital.cn.com.mobilenurse.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.view.TextureView;

/**
 * 复制 bitmap的渲染
 */
public class ImageRenderer implements Render {

    private TextureView mTextureView;
    private Paint       mPaint;
    private Rect        mSrcRect;
    private Rect        mDstRect;

    public ImageRenderer(TextureView textureView) {
        mTextureView = textureView;
    }

    @Override
    public void onDraw(int frameIndex, Bitmap bitmap) {
        if (frameIndex < 0) {
            return;
        }

        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
    }

    @Override
    public void onError(String message) {

    }

    private int getHeight() {
        return mTextureView.getHeight();
    }

    private int getWidth() {
        return mTextureView.getWidth();
    }

    public void clearDraw() {
        if (getHeight() == 0 || getWidth() == 0) {
            return;
        }
        Canvas canvas = mTextureView.lockCanvas();
        if (canvas == null) {
            return;
        }
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        mTextureView.unlockCanvasAndPost(canvas);
    }
}
