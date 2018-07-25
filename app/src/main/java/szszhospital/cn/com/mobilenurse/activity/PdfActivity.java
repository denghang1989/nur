package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;

import java.io.File;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPdfBinding;

public class PdfActivity extends BaseActivity<ActivityPdfBinding> {

    private static final String KEY_DATA = "data";
    private File mFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    public static void startPdfActivity(Context context, File file) {
        Intent intent = new Intent(context, PdfActivity.class);
        intent.putExtra(KEY_DATA, file);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mFile = (File) getIntent().getSerializableExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.pdfView.fromFile(mFile).load();
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
