package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityImageBinding;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsImageActivity extends BaseActivity<ActivityImageBinding> {

    private static final String KEY_DATA = "data";

    public static void startImageActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsImageActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

}
