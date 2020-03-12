package szszhospital.cn.com.mobilenurse.view.imageplayer;

import java.util.List;

/**
 * 2018/8/30 21
 */
public interface Player {
    void next();

    void prev();

    void setSource(List<String> sources);

    void seekTo(int frameIndex);

    int getCurrentFrameIndex();

    String getCurrentFrame();

    void onDestroyed();

    void setRenderListener(OnRenderListener onRenderListener);

    void play();

    void stop();
}
