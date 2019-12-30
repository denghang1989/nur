package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPdfReportBinding;
import szszhospital.cn.com.mobilenurse.fragemt.FtpReportFragment;

/**
 * pdf查看工具
 */
public class PdfReportActivity extends BaseActivity<ActivityPdfReportBinding> {

    private static final String KEY_DATA     = "data";
    private static final String KEY_FTP_PATH = "ftpPath";
    private              String mFilePath;
    private              String mFtpPath;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf_report;
    }

    public static void startPdfReportActivity(Context context, String file, String ftpPath) {
        Intent intent = new Intent(context, PdfReportActivity.class);
        intent.putExtra(KEY_DATA, file);
        intent.putExtra(KEY_FTP_PATH, ftpPath);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mFilePath = getIntent().getStringExtra(KEY_DATA);
        mFtpPath = getIntent().getStringExtra(KEY_FTP_PATH);
    }

    @Override
    protected void initView() {
        super.initView();
        if (mFtpPath.startsWith("ftp")) {
            FtpReportFragment ftpReportFragment = FtpReportFragment.newInstance(mFilePath, mFtpPath);
            loadRootFragment(R.id.container, ftpReportFragment);
        } else if (mFilePath.startsWith("http")) {

        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }
}
