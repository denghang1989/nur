package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;

import java.util.List;

/**
 * 复制bitmap的播放
 */
public class ImagePlayer implements Player {
    private Context      mContext;
    private List<String> mSource;
    private Render       mRender;
    private int          mCurrentFrameIndex;
    private boolean      isPlaying;

    public ImagePlayer(Context context, Render render) {
        mContext = context;
        mRender = render;
    }

    @Override
    public void next() {
        isPlaying = false;
    }

    @Override
    public void preV() {
        isPlaying = false;
    }

    @Override
    public void setSource(List<String> sources) {
        mSource = sources;
    }

    @Override
    public void seekTo(int frameIndex) {

    }

    @Override
    public void registerCallback(Callback callback) {

    }

    @Override
    public void unregisterCallback(Callback callback) {

    }
}
