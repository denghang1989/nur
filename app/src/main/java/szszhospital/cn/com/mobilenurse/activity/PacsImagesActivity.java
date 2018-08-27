package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;

import com.blankj.utilcode.util.NetworkUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.xiuyukeji.pictureplayerview.PicturePlayerView;
import com.xiuyukeji.pictureplayerview.interfaces.OnStopListener;
import com.xiuyukeji.pictureplayerview.interfaces.OnUpdateListener;

import java.io.File;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.PacsFtpAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsImagesBinding;
import szszhospital.cn.com.mobilenurse.factory.ReportFactory;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsImagesPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName_Table;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.Contants;
import szszhospital.cn.com.mobilenurse.utils.DcmUtil;
import szszhospital.cn.com.mobilenurse.utils.FileDownUtil;
import szszhospital.cn.com.mobilenurse.utils.FtpUtil;

public class PacsImagesActivity extends BasePresentActivity<ActivityPacsImagesBinding, PacsImagesPresenter> implements PacsImagesContract.View {

    private static final String TAG       = "PacsImagesActivity";
    private static final String KEY_DATA  = "data";
    public static final  String FTP_PATH  = "172.18.0.143";
    public static final  String USER_NAME = "annetftp";
    public static final  String PASSWORD  = "annet";
    private PacsOrder         mPacsorder;
    private FtpUtil           mFtp;
    private PacsFtpAdapter    mAdapter;
    private List<DcmName>     mCurrentDcmNames;
    private int               mSelectImage;
    private int               mSlop;
    private PicturePlayerView mPicturePlayerView;

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
        mFtp = new FtpUtil();
        FtpConnect();
        super.init();
        mPacsorder = getIntent().getParcelableExtra(KEY_DATA);
        mAdapter = new PacsFtpAdapter(R.layout.item_pacs_ftp, mFtp, this);
        mSlop = 50;
    }

    private void FtpConnect() {
        if (NetworkUtils.isConnected()) {
            App.getAsynHandler().post(() -> {
                try {
                    mFtp.connect(FTP_PATH, USER_NAME, PASSWORD);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }

    @Override
    protected void initView() {
        super.initView();
        mPicturePlayerView = mDataBinding.player;
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsImages(mPacsorder.TStudyNo, ReportFactory.getInstance(mPacsorder).openImage(mPacsorder));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSelectImage = 0;
                PacsImagePath item = mAdapter.getItem(position);
                mCurrentDcmNames = new Select().from(DcmName.class).where(DcmName_Table.IMAGEPATH.eq(item.IMAGEPATH)).queryList();
                changeMark(position);
                String[] pathArray = new String[mCurrentDcmNames.size()];
                for (int i = 0; i < mCurrentDcmNames.size(); i++) {
                    DcmName dcmName = mCurrentDcmNames.get(i);
                    String imagePath = dcmName.IMAGEPATH;
                    String imagename = dcmName.IMAGENAME;
                    File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename);
                    if (file.exists() && file.length() > 0) {
                        if (i == 0) {
                            Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into(mDataBinding.container);
                        }
                    } else {
                        App.getAsynHandler().post(() -> FileDownUtil.downFileAndChangedPng(Contants.PACS_PATH + imagePath + imagename, file.getAbsolutePath(), null));
                    }
                    File pngFile = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename.replace(".dcm", ".png"));
                    pathArray[i] = pngFile.getAbsolutePath();
                }
                mPicturePlayerView.setDataSource(pathArray, 10000);
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
                        mPicturePlayerView.start();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getX();
                        float moveY = event.getY();
                        // x 轴移动
                        if (Math.abs(moveX - mDownX) > Math.abs(moveY - mDownY)) {
                            if (mCurrentDcmNames != null && mCurrentDcmNames.size() > 1) {
                                // --->移动
                                if ((moveX - mDownX > 0) && (moveX - mDownX > mSlop) && (mSelectImage < mCurrentDcmNames.size() - 1)) {
                                    mSelectImage = mSelectImage + 1;
                                    mDownX = mDownX + mSlop;
                                    changedPacsImage();
                                }
                                // <---移动
                                if ((moveX - mDownX < 0) && (Math.abs(moveX - mDownX) > mSlop) && (mSelectImage > 0)) {
                                    mSelectImage = mSelectImage - 1;
                                    mDownX = mDownX + mSlop;
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

        mPicturePlayerView.setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(int frameIndex) {

            }
        });

        mPicturePlayerView.setOnStopListener(new OnStopListener() {
            @Override
            public void onStop() {

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
        changText(mSelectImage + 1);
    }

    private void changeMark(int position) {
        mAdapter.setSelected(position);
        changText(1);
        mAdapter.notifyDataSetChanged();
    }

    private void changText(int position) {
        mDataBinding.mark.setText(position + "/" + mCurrentDcmNames.size());
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
        mAdapter.setNewData(pacsImagePaths);
    }

    @Override
    protected PacsImagesPresenter initPresenter() {
        return new PacsImagesPresenter(mFtp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFtp.disconnect();
        mPicturePlayerView.release();
    }

}
