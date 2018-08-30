package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import java.io.File;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPdfBinding;
import szszhospital.cn.com.mobilenurse.utils.FileCallback;
import szszhospital.cn.com.mobilenurse.utils.FileDownUtil;

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
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.loading.setVisibility(View.VISIBLE);
        App.getAsynHandler().post(() -> FileDownUtil.downFileAndChangedPng(mPath, new FileCallback() {
            @Override
            public void success(File file) {
                mDataBinding.pdfView.fromFile(file).onLoad(page -> mDataBinding.loading.setVisibility(View.GONE)).load();
            }

            @Override
            public void error(Exception e) {
                ToastUtils.showShort("pdf文件不存在！");
            }
        }));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getAsynHandler().removeCallbacksAndMessages(null);
    }
}
