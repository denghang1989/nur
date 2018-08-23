package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.adeel.library.easyFTP;

import org.apache.commons.net.ftp.FTPFile;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsImagesBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsImagesPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.Contants;

public class PacsImagesActivity extends BasePresentActivity<ActivityPacsImagesBinding, PacsImagesPresenter> implements PacsImagesContract.View {

    private static final String TAG       = "PacsImagesActivity";
    private static final String KEY_DATA  = "data";
    private static final String FTP_PATH  = "172.18.0.143";
    private static final String USER_NAME = "annetftp";
    private static final String PASSWORD  = "annet";
    private PacsOrder mPacsorder;
    private easyFTP   mFtp;

    public static void startPacsImagesActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsImagesActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pacs_images;
    }

    @Override
    protected void init() {
        super.init();
        mPacsorder = getIntent().getParcelableExtra(KEY_DATA);
        mFtp = new easyFTP();
        FtpConnect();
    }

    private void FtpConnect() {
        try {
            mFtp.connect(FTP_PATH, USER_NAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsImages("A1125335", "01");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showImages() {

    }

    @Override
    public void getRealImagePath(List<PacsImagePath> pacsImagePaths) {
        App.getAsynHandler().post(() -> {
            try {
                boolean connected = mFtp.getFtpClient().isConnected();
                if (!connected) {
                    FtpConnect();
                }
                for (int i = 0; i < pacsImagePaths.size(); i++) {
                    PacsImagePath obj = pacsImagePaths.get(i);
                    String ftpPath = obj.IMAGEPATH;
                    FTPFile[] ftpFiles = mFtp.getFtpClient().listFiles(ftpPath);
                    for (int j = 0; j < ftpFiles.length; j++) {
                        if ((i == 0) && (j == 0)) {
                            Log.d(TAG, "getRealImagePath: "+ftpPath + ftpFiles[j].getName());
                            mFtp.downloadFile(ftpPath + ftpFiles[j].getName(), Contants.PACS_DCM_DOWNLOAD_PATH);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected PacsImagesPresenter initPresenter() {
        return new PacsImagesPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFtp.disconnect();
    }
}
