package szszhospital.cn.com.mobilenurse.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static szszhospital.cn.com.mobilenurse.annotations.FitSource.FIT_CENTER;
import static szszhospital.cn.com.mobilenurse.annotations.FitSource.FIT_CROP;
import static szszhospital.cn.com.mobilenurse.annotations.FitSource.FIT_HEIGHT;
import static szszhospital.cn.com.mobilenurse.annotations.FitSource.FIT_WIDTH;

/**
 * 缩放类型
 *
 * @author Created by jz on 2017/6/6 10:43
 */
@IntDef({FIT_WIDTH, FIT_HEIGHT, FIT_CENTER, FIT_CROP})
@Retention(RetentionPolicy.SOURCE)
public @interface FitSource {
    int FIT_WIDTH = 0;
    int FIT_HEIGHT = 1;
    int FIT_CENTER = 2;
    int FIT_CROP = 3;
}