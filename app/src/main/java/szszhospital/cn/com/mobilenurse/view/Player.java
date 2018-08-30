package szszhospital.cn.com.mobilenurse.view;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * 2018/8/30 21
 */
public interface Player {
    void next();

    void preV();

    void setSource(List<String> sources);

    void seekTo(int frameIndex);

    void registerCallback(Callback callback);

    void unregisterCallback(Callback callback);

    public interface  Callback {

        void onSwitchLast(@Nullable int last);

        void onSwitchNext(@Nullable int next);

        void onComplete(@Nullable int next);
    }
}
