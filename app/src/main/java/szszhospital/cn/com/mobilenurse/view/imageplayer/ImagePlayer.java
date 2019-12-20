package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.TextureView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.List;

/**
 * bitmap的播放；
 * bitmap 缓存使用glide 来管理
 */
public class ImagePlayer implements Player {
    private Context         mContext;
    private ImageRenderer   mRender;
    private ImagePlayerList mPlayerList;

    public ImagePlayer(Context context, TextureView textureView) {
        mContext = context;
        mPlayerList = new ImagePlayerList();
        mRender = new ImageRenderer(textureView);
    }

    @Override
    public void next() {
        mPlayerList.next();
        play();
    }

    @Override
    public void play() {
        clearMessage();
        SchedulerUtil.getAsynHandler().post(() -> {
            int playingIndex = mPlayerList.getPlayingIndex();
            File file = new File(mPlayerList.getCurrentSource());
            if (file.exists()) {
                try {
                    FutureTarget<Bitmap> submit = Glide.with(mContext).asBitmap().load(file).submit();
                    Bitmap bitmap = submit.get();
                    SchedulerUtil.getMainHandler().post(() -> mRender.onDraw(playingIndex, bitmap));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void clearMessage() {
        SchedulerUtil.getMainHandler().removeCallbacksAndMessages(null);
        SchedulerUtil.getAsynHandler().removeCallbacksAndMessages(null);
    }

    @Override
    public void preV() {
        mPlayerList.prev();
        play();
    }

    @Override
    public void setSource(List<String> sources) {
        mPlayerList.setResource(sources);
    }

    @Override
    public void seekTo(int frameIndex) {
        mPlayerList.seekTo(frameIndex);
        play();
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
        clearMessage();
        mRender.onClear();
    }

    @Override
    public void setRenderListener(RenderListener renderListener) {
        mRender.setOnRenderListener(renderListener);
    }

    @Override
    public void stop() {
        clearMessage();
    }
}
