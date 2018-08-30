package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 负责bitmap的播放展示,画面不存在播放，是通过手势来一帧一帧渲染
 */
public class ImagePlayerView extends FrameLayout implements TextureView.SurfaceTextureListener {

    private View                 mRootView;
    private int                  mCacheNumber;
    private TextureView          mTextureView;
    private ProgressBar          mProgressBar;
    private OnImagePlayerChanged mImagePlayerChanged;
    private int                  mCurrentFrameIndex;
    private List<String>         mDataFiles;

    public ImagePlayerView(Context context) {
        this(context, null);
    }

    public ImagePlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_player, this, true);
        initView(mRootView);
        initRender();
        initPlayer(context);
    }

    private void initRender() {
    }

    private void initPlayer(Context context) {
        Player player = new ImagePlayer(context,mDataFiles);
    }

    private void initView(View rootView) {
        mTextureView = rootView.findViewById(R.id.textureView);
        mTextureView.setSurfaceTextureListener(this);
        mProgressBar = rootView.findViewById(R.id.loading);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ImagePlayerView);
        mCacheNumber = ta.getInt(R.styleable.ImagePlayerView_cacheNumber, 12);
        ta.recycle();
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
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        if (mImagePlayerChanged != null) {
            mImagePlayerChanged.update(mCurrentFrameIndex);
        }
    }

    public void setSrcFiles(List<String> list) {
        mDataFiles = list;
        mCurrentFrameIndex = 0;
    }
}