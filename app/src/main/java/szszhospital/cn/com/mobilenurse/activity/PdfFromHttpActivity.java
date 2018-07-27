package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPdfBinding;

/**
 * pdf查看工具
 */
public class PdfFromHttpActivity extends BaseActivity<ActivityPdfBinding> {

    private static final String KEY_DATA = "data";
    private String mPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    public static void startPdfActivity(Context context, String file) {
        Intent intent = new Intent(context, PdfFromHttpActivity.class);
        intent.putExtra(KEY_DATA, file);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mPath = getIntent().getStringExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.pdfView.fromUri(Uri.parse(mPath)).load();
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
