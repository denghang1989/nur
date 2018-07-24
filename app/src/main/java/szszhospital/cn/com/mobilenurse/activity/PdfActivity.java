package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPdfBinding;

public class PdfActivity extends BaseActivity<ActivityPdfBinding> {

    private static final String KEY_DATA = "data";
    private Uri mUri;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    public static void startPdfActivity(Context context, Uri uri) {
        Intent intent = new Intent(context, PacsDetailActivity.class);
        intent.putExtra(KEY_DATA, uri);
        context.startActivity(intent);
    }

    public static void startPdfActivity(Context context) {
        Intent intent = new Intent(context, PacsDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
       // mUri = getIntent().getParcelableExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
       // mDataBinding.pdfView.fromUri(mUri);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
