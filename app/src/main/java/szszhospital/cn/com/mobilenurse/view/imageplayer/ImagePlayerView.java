package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

import java.util.Arrays;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 负责bitmap的播放展示,画面不存在播放，是通过手势来一帧一帧渲染
 */
public class ImagePlayerView extends TextureView implements TextureView.SurfaceTextureListener {

    private TextureView          mTextureView;
    private OnImagePlayerChanged mImagePlayerChanged;
    private Player               mPlayer;

    public ImagePlayerView(Context context) {
        this(context, null);
    }

    public ImagePlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPlayer(context);
    }

    private void initPlayer(Context context) {
        mPlayer = new ImagePlayer(context,this);
    }

    private void initView(View rootView) {
        mTextureView = rootView.findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        if (mImagePlayerChanged != null) {
            mImagePlayerChanged.onCreate();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (mImagePlayerChanged != null) {
            mImagePlayerChanged.onDestroyed();
        }
        mPlayer.onDestroyed();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        if (mImagePlayerChanged != null) {
            mImagePlayerChanged.update(mPlayer.getCurrentFrameIndex());
        }
    }

    public void next() {
        mPlayer.next();
    }

    public void prev() {
        mPlayer.preV();
    }

    public String getCurrentFile() {
        return mPlayer.getCurrentFrame();
    }

    public int getCurrentIndex() {
        return mPlayer.getCurrentFrameIndex();
    }

    public void clear() {
        mPlayer.onDestroyed();
    }

    public void setDataSource(String[] pathArray) {
        mPlayer.setSource(Arrays.asList(pathArray));
    }

    public void setOnRenderListener(RenderListener callback){
        mPlayer.setRenderListener(callback);
    }
}
