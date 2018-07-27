package szszhospital.cn.com.mobilenurse.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * 2016/10/24 09
 */
public class ImageLoader {

    private ImageLoader() {
    }

    private static class Singleton {
        private static final ImageLoader LOADER = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return Singleton.LOADER;
    }

    public void displayImage(Context context, String url, ImageView view) {
        Glide.with(context).
                load(url).
                into(view);
    }

    public void displayImage(Context context, int url, ImageView view) {
        Glide.with(context).
                load(url).
                into(view);
    }

    public void displayImage(Activity activity, String url, ImageView view) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).
                    load(url).
                    into(view);
        }
    }

    public void displayImage(Fragment fragment, String url, ImageView view) {
        if (!fragment.isDetached()) {
            Glide.with(fragment).
                    load(url).
                    into(view);
        }
    }

    public void displayCircleImage(Activity activity, String url, ImageView view) {
        if (!activity.isDestroyed()) {
            Glide.with(activity)
                    .load(url)
                    .into(view);
        }
    }

    public void displayCircleImage(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .into(view);
    }

    public void displayCircleImage(Fragment fragment, String url, ImageView view) {
        if (!fragment.isDetached()) {
            Glide.with(fragment).
                    load(url).
                    into(view);
        }
    }

    public void displayImageGray(Context context, String url, ImageView view) {
        Glide.with(context).
                load(url).
                into(view);
    }

}
