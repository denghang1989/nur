package szszhospital.cn.com.mobilenurse.view.imageplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.TextureView;

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
    private ImageRenderer   mRender;
    private ImagePlayerList mPlayerList;

    public ImagePlayer(Context context, TextureView textureView) {
        mContext = context;
        mPlayerList = new ImagePlayerList();
        mRender = new ImageRenderer(textureView);
    }

    @Override
    public void next() {
        String next = mPlayerList.next();
        play(next);
    }

    @Override
    public void play(String path) {
        int playingIndex = mPlayerList.getPlayingIndex();
        File file = new File(path);
        if (file.exists()) {
            try {
                FutureTarget<Bitmap> submit = Glide.with(mContext).asBitmap().load(file).submit();
                Bitmap bitmap = submit.get();
                mRender.onDraw(playingIndex, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                mRender.onError(e.getMessage());
            }
        }
    }

    @Override
    public void preV() {
        String prev = mPlayerList.prev();
        play(prev);
    }

    @Override
    public void setSource(List<String> sources) {
        mPlayerList.setResource(sources);
    }

    @Override
    public void seekTo(int frameIndex) {
        String seek = mPlayerList.seekTo(frameIndex);
        play(seek);
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
        mRender.onClear();
    }

    @Override
    public void setRenderListener(RenderListener renderListener) {
        mRender.setOnRenderListener(renderListener);
    }

    @Override
    public void stop() {
        mRender.onClear();
    }
}
