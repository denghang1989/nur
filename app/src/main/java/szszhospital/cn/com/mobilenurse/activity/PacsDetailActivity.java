package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsDetailActivity extends BaseActivity {

    private static final String KEY_DATA = "data";

    public static void startPacsDetailActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsDetailActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pacs_detail;
    }

    @Override
    protected void initView() {
        super.initView();

    }
}
