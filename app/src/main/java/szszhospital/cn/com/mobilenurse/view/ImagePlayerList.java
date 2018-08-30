package szszhospital.cn.com.mobilenurse.view;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/30 22
 * 循环取图，不考虑最后，第一帧画面的问题
 */
public class ImagePlayerList {

    public static final int NO_POSITION = -1;

    private int mPlayingIndex;

    private List<String> mList;

    public ImagePlayerList() {
    }

    public List<String> getImageList() {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        return mList;
    }

    public void setResource(@NonNull List<String> list) {
        mList = list;
        //重新初始化数据
        mPlayingIndex = 0;
    }

    public int getPlayingIndex() {
        return mPlayingIndex;
    }

    public void setPlayingIndex(int playingIndex) {
        mPlayingIndex = playingIndex;
    }

    public int getSourceCount() {
        return mList == null ? 0 : mList.size();
    }

    public String getCurrentSource() {
        if (NO_POSITION != mPlayingIndex) {
            return mList.get(mPlayingIndex);
        }
        return null;
    }

    public boolean hasPrev() {
        return mList != null && mList.size() != 0;
    }

    public boolean hasNext() {
        return mList != null && mList.size() != 0;
    }

    public String prev() {
        int newIndex = mPlayingIndex - 1;
        if (newIndex < 0) {
            newIndex = mList.size() - 1;
        }
        mPlayingIndex = newIndex;
        return mList.get(mPlayingIndex);
    }

    public String next() {
        int newIndex = mPlayingIndex + 1;
        if (newIndex >= mList.size()) {
            newIndex = 0;
        }
        mPlayingIndex = newIndex;
        return mList.get(mPlayingIndex);
    }
}
