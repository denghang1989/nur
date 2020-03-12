package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;

import java.io.File;
import java.io.IOException;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentFtpBinding;
import szszhospital.cn.com.mobilenurse.utils.FTPUtil;

import static szszhospital.cn.com.mobilenurse.utils.Contants.PDF_DOWNLOAD_PATH;

/**
 * //ftp^192.168.199.63^21^PIS^BAFY@2015^5
 */
public class FtpReportFragment extends BaseFragment<FragmentFtpBinding> {
    private static final String  KEY_FILEPATH = "File";
    private static final String  KEY_FTPPATH  = "ftpPath";
    private              String  mFilePath;
    private              FTPUtil mFtpCli;

    public static FtpReportFragment newInstance(String filePath, String ftpPath) {
        Bundle args = new Bundle();
        args.putString(KEY_FILEPATH, filePath);
        args.putString(KEY_FTPPATH, ftpPath);
        FtpReportFragment fragment = new FtpReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ftp;
    }

    @Override
    protected void init() {
        super.init();
        Bundle arguments = getArguments();
        mFilePath = arguments.getString(KEY_FILEPATH);
        String ftpPath = arguments.getString(KEY_FTPPATH);
        if (!StringUtils.isEmpty(ftpPath)) {
            String[] split = ftpPath.split("\\^");
            mFtpCli = FTPUtil.createFtpCli(split[1], Integer.parseInt(split[2]), split[3], split[4], "UTF-8", "");
        }
        connect();
    }

    private void connect() {
        App.getAsynHandler().post(() -> {
            try {
                mFtpCli.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mDataBinding.loading.setVisibility(View.VISIBLE);
        App.getAsynHandler().post(() -> {
            File localFile = new File(PDF_DOWNLOAD_PATH + mFilePath);
            if (localFile.exists()) {
                mDataBinding.pdfView.fromFile(localFile).onLoad(page -> mDataBinding.loading.setVisibility(View.GONE)).load();
            } else {
                mFtpCli.downloadFileFromFTP(mFilePath, localFile);
                mDataBinding.pdfView.fromFile(localFile).onLoad(page -> mDataBinding.loading.setVisibility(View.GONE)).load();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disconnect();
    }

    private void disconnect() {
        App.getAsynHandler().removeCallbacksAndMessages(null);
        App.getAsynHandler().post(() -> mFtpCli.disconnect());
    }
}
