package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;
import android.graphics.Bitmap;

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
    private boolean         isPlaying;
    private ImagePlayerList mPlayerList;

    public ImagePlayer(Context context, Render render) {
        mContext = context;
        mRender = render;
        mPlayerList = new ImagePlayerList();
    }

    @Override
    public void next() {
        isPlaying = false;
        if (mPlayerList.hasNext()) {
            String next = mPlayerList.next();
            File nextFile = new File(next);
            int playingIndex = mPlayerList.getPlayingIndex();
            FutureTarget<Bitmap> futureTarget = Glide.with(mContext).asBitmap().load(nextFile).submit();
            try {
                Bitmap bitmap = futureTarget.get();
                mRender.onDraw(playingIndex,bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                mRender.onError(e.getMessage());
            }
        }
    }

    @Override
    public void preV() {
        isPlaying = false;
        if (mPlayerList.hasPrev()) {
            String prev = mPlayerList.prev();
            File prevFile = new File(prev);
            int playingIndex = mPlayerList.getPlayingIndex();
            FutureTarget<Bitmap> futureTarget = Glide.with(mContext).asBitmap().load(prevFile).submit();
            try {
                Bitmap bitmap = futureTarget.get();
                mRender.onDraw(playingIndex,bitmap);
            } catch (Exception e) {
                e.printStackTrace();
                mRender.onError(e.getMessage());
            }
        }
    }

    @Override
    public void setSource(List<String> sources) {
        mPlayerList.setResource(sources);
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
