package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.List;

/**
 * 复制bitmap的播放；
 * bitmap 缓存使用glide 来管理
 */
public class ImagePlayer implements Player {
    private Context         mContext;
    private Render          mRender;
    private ImagePlayerList mPlayerList;
    private Handler         mHandler;

    public ImagePlayer(Context context, Render render) {
        mContext = context;
        mRender = render;
        mPlayerList = new ImagePlayerList();
        mHandler = new Handler();
    }

    @Override
    public void next() {
        if (mPlayerList.hasNext()) {
            String next = mPlayerList.next();
            play(next);
        }
    }

    private void play(String next) {
        File nextFile = new File(next);
        int playingIndex = mPlayerList.getPlayingIndex();
        FutureTarget<Bitmap> futureTarget = Glide.with(mContext).asBitmap().load(nextFile).submit();
        try {
            Bitmap bitmap = futureTarget.get();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRender.onDraw(playingIndex, bitmap);
                }
            }, 10);
        } catch (Exception e) {
            e.printStackTrace();
            mRender.onError(e.getMessage());
        }
    }

    @Override
    public void preV() {
        if (mPlayerList.hasPrev()) {
            String prev = mPlayerList.prev();
            play(prev);
        }
    }

    @Override
    public void setSource(List<String> sources) {
        mPlayerList.setResource(sources);
    }

    @Override
    public void seekTo(int frameIndex) {
        if (mPlayerList.getSourceCount() > 0) {
            String seek = mPlayerList.seekTo(frameIndex);
            play(seek);
        }
    }

    @Override
    public void registerCallback(Callback callback) {

    }

    @Override
    public void unregisterCallback(Callback callback) {

    }

    @Override
    public int getCurrentFrameIndex() {
        return mPlayerList.getPlayingIndex();
    }

    @Override
    public String getCurrentFrame() {
        return mPlayerList.getCurrentSource();
    }

    @Override
    public void onDestroyed() {
        mHandler.removeCallbacksAndMessages(null);
    }
}
