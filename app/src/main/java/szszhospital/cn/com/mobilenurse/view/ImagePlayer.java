package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.TextureView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 复制bitmap的播放；
 * bitmap 缓存使用glide 来管理
 */
public class ImagePlayer implements Player {
    private static final String TAG = "ImagePlayer";
    private Context           mContext;
    private Render            mRender;
    private ImagePlayerList   mPlayerList;
    private Handler           mAsynHandler;
    private AsynHandlerThread mHandlerThread;
    private CopyOnWriteArrayList<Callback> mCallbacks = new CopyOnWriteArrayList<>();
    private Handler                        mHandler   = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int what = msg.what;
            Log.d(TAG, "handleMessage: "+what);
            switch (what) {
                case 1:
                    for (int i = 0; i < mCallbacks.size(); i++) {
                        Callback callback = mCallbacks.get(i);
                        callback.onComplete(msg.arg1);
                    }
                    break;
            }
            return true;
        }
    });

    public ImagePlayer(Context context, TextureView textureView) {
        mContext = context;
        mPlayerList = new ImagePlayerList();
        mRender = new ImageRenderer(textureView, 3, mHandler);
        mHandlerThread = new AsynHandlerThread("ImagePlayer");
        mHandlerThread.start();
        mAsynHandler = new Handler(mHandlerThread.getLooper());
    }

    @Override
    public void next() {
        if (mPlayerList.hasNext()) {
            String next = mPlayerList.next();
            play(next);
        }
    }

    private void play(String next) {
        mAsynHandler.removeCallbacksAndMessages(null);
        mHandler.removeCallbacksAndMessages(null);
        mAsynHandler.post(new Runnable() {
            @Override
            public void run() {
                File file = new File(next);
                int playingIndex = mPlayerList.getPlayingIndex();
                try {
                    FutureTarget<Bitmap> submit = Glide.with(mContext).asBitmap().load(file).submit();
                    Bitmap bitmap = submit.get();
                    mHandler.post(() -> mRender.onDraw(playingIndex, bitmap));
                } catch (Exception e) {
                    e.printStackTrace();
                    mRender.onError(e.getMessage());
                }
            }
        });
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
        if (!mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void unregisterCallback(Callback callback) {
        if (mCallbacks.contains(callback)) {
            mCallbacks.remove(callback);
        }
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
        mAsynHandler.removeCallbacksAndMessages(null);
        mHandler.removeCallbacksAndMessages(null);
    }

    public class AsynHandlerThread extends HandlerThread {

        public AsynHandlerThread(String name) {
            super(name);
        }
    }
}
