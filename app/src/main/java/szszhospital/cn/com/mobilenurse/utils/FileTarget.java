package szszhospital.cn.com.mobilenurse.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.util.ImageUtils;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

public class FileTarget extends SimpleTarget<File> {
    private static final String TAG = "FileTarget";

    private ImageView mImageView;

    public FileTarget(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
        Log.d(TAG, "onResourceReady: "+ resource.getName());
        File file = DcmUtil.readFile(resource.getAbsolutePath());
        mImageView.setImageBitmap(ImageUtils.getBitmap(file));
    }
}
