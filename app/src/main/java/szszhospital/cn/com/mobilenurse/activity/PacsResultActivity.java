package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsResultActivity extends BasePresentActivity {

    private static final String KEY_DATA = "data";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pacs_result;
    }

    public static void startPacsResultActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsResultActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }
}
