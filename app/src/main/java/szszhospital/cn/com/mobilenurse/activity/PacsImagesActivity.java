package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.PacsFtpAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsImagesBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsImagesPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName_Table;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.Contants;
import szszhospital.cn.com.mobilenurse.utils.DcmUtil;
import szszhospital.cn.com.mobilenurse.utils.FtpUtil;

public class PacsImagesActivity extends BasePresentActivity<ActivityPacsImagesBinding, PacsImagesPresenter> implements PacsImagesContract.View {

    private static final String TAG       = "PacsImagesActivity";
    private static final String KEY_DATA  = "data";
    public static final  String FTP_PATH  = "172.18.0.143";
    public static final  String USER_NAME = "annetftp";
    public static final  String PASSWORD  = "annet";
    private PacsOrder      mPacsorder;
    private FtpUtil        mFtp;
    private PacsFtpAdapter mAdapter;
    private List<DcmName>  mCurrentDcmNames;
    private int            mSelectImage;
    private int            mSlop;

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
        mFtp = new FtpUtil();
        FtpConnect();
        mAdapter = new PacsFtpAdapter(R.layout.item_pacs_ftp, mFtp, this);
        mSlop = ViewConfiguration.get(this).getScaledTouchSlop();
    }

    private void FtpConnect() {
        try {
            if (NetworkUtils.isConnected()) {
                mFtp.connect(FTP_PATH, USER_NAME, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsImages("A1125335", "01");
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                changeMark(position);
                mSelectImage = 0;
                PacsImagePath item = mAdapter.getItem(position);
                mCurrentDcmNames = new Select().from(DcmName.class).where(DcmName_Table.IMAGEPATH.eq(item.IMAGEPATH)).queryList();
                for (int i = 0; i < mCurrentDcmNames.size(); i++) {
                    DcmName dcmName = mCurrentDcmNames.get(i);
                    String imagePath = dcmName.IMAGEPATH;
                    String imagename = dcmName.IMAGENAME;
                    File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename);
                    if (file.exists()) {
                        if (i == 0) {
                            Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into(mDataBinding.container);
                        }
                    } else {
                        App.getAsynHandler().post(() -> mFtp.downloadFile(imagePath + imagename, file.getAbsolutePath(), null));
                    }
                }
            }
        });


        mDataBinding.touch.setOnTouchListener(new View.OnTouchListener() {

            private float mDownY;
            private float mDownX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mDownX = event.getX();
                        mDownY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getX();
                        float moveY = event.getY();
                        // x 轴移动
                        if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {
                            if (mCurrentDcmNames != null && mCurrentDcmNames.size() > 1) {
                                // --->移动
                                if ((moveX - mDownX > 0) && (moveX - mDownX > mSlop) && (mSelectImage < mCurrentDcmNames.size())) {
                                    mSelectImage = mSelectImage + 1;
                                    changedPacsImage();
                                }
                                // <---移动
                                if ((moveX - mDownX < 0) && (Math.abs(moveX - mDownX) > mSlop) && (mSelectImage > 0)) {
                                    mSelectImage = mSelectImage - 1;
                                    changedPacsImage();
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }
                return true;
            }
        });
    }

    private void changedPacsImage() {
        DcmName dcmName = mCurrentDcmNames.get(mSelectImage);
        String imagename = dcmName.IMAGENAME;
        File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename);
        if (file.exists()) {
            Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into(mDataBinding.container);
        }
    }

    private void changeMark(int position) {
        mAdapter.setSelected(position);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void getRealImagePath(List<PacsImagePath> pacsImagePaths) {
        showProgress();
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
                        String fileName = ftpFiles[j].getName();
                        DcmName dcmName = new DcmName();
                        dcmName.IMAGENAME = fileName;
                        dcmName.IMAGEPATH = ftpPath;
                        dcmName.save();
                        if (j == 0) {
                            obj.thumbnailPath = ftpPath + fileName;
                            obj.name = fileName;
                        }
                    }
                    obj.save();
                }
                runOnUiThread(() -> {
                    mAdapter.setNewData(pacsImagePaths);
                    hideProgress();
                });
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
