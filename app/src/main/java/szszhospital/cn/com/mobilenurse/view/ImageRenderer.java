package szszhospital.cn.com.mobilenurse.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;

import static com.xiuyukeji.pictureplayerview.annotations.FitSource.FIT_CENTER;
import static com.xiuyukeji.pictureplayerview.annotations.FitSource.FIT_CROP;
import static com.xiuyukeji.pictureplayerview.annotations.FitSource.FIT_HEIGHT;
import static com.xiuyukeji.pictureplayerview.annotations.FitSource.FIT_WIDTH;

/**
 * 复制 bitmap的渲染
 */
public class ImageRenderer implements Render {

    private static final int WIDTH = 0, HEIGHT = 1;
    private static final String TAG = "ImageRenderer";

    private TextureView mTextureView;
    private Paint       mPaint;
    private Rect        mSrcRect;
    private Rect        mDstRect;
    private int         mScaleType;
    private float       mScale;
    private int         mWidth;
    private int         mHeight;
    private int state = WIDTH;
    private Handler mHandler;

    public ImageRenderer(TextureView textureView, int scaleType, Handler handler) {
        mTextureView = textureView;
        mScaleType = scaleType;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mSrcRect = new Rect();
        mDstRect = new Rect();
        mHandler = handler;
    }

    @Override
    public void onDraw(int frameIndex, Bitmap bitmap) {
        Log.d(TAG, "onDraw: " + frameIndex);
        if (frameIndex < 0) {
            return;
        }

        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }

        calculateScale(bitmap.getWidth(), bitmap.getHeight());

        Canvas canvas = mTextureView.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);// 清空画布

            int left = 0;
            int top = 0;
            if (state == WIDTH) {
                top = calculateTop();
            } else {
                left = calculateLeft();
            }
            int right = left + mWidth;
            int bottom = top + mHeight;

            mSrcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            mDstRect.set(left, top, right, bottom);
            canvas.drawBitmap(bitmap, mSrcRect, mDstRect, mPaint);
            Message message = mHandler.obtainMessage();
            message.what = 1;
            message.arg1 = frameIndex;
            mHandler.sendMessage(message);
            mTextureView.unlockCanvasAndPost(canvas);
        }
    }

    private int calculateLeft() {
        return (getWidth() - mWidth) / 2;
    }

    private int calculateTop() {
        return (getHeight() - mHeight) / 2;
    }

    //这里默认只计算第一张图片的大小，如果接下来的图片大小不一致可能会变形
    private void calculateScale(int width, int height) {
        if (mScale != 0) {
            return;
        }
        switch (mScaleType) {
            case FIT_WIDTH:
                callWidth(width, height);
                break;
            case FIT_HEIGHT:
                callHeight(width, height);
                break;
            case FIT_CENTER:
                if (getWidth() * height > getHeight() * width) {
                    callHeight(width, height);
                } else {
                    callWidth(width, height);
                }
                break;
            case FIT_CROP:
                if (getWidth() * height > getHeight() * width) {
                    callWidth(width, height);
                } else {
                    callHeight(width, height);
                }
                break;
            default:
                break;
        }
    }

    private void callWidth(int width, int height) {
        mScale = getWidth() / (float) width;
        mWidth = getWidth();
        mHeight = (int) (height * mScale);
        state = WIDTH;
    }

    private void callHeight(int width, int height) {
        mScale = getHeight() / (float) height;
        mWidth = (int) (width * mScale);
        mHeight = getHeight();
        state = HEIGHT;
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
