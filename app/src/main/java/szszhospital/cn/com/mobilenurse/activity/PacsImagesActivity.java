package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;

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

public class PacsImagesActivity extends BasePresentActivity<ActivityPacsImagesBinding, PacsImagesPresenter> implements PacsImagesContract.View, View.OnTouchListener {

    private static final String TAG      = "PacsImagesActivity";
    private static final String KEY_DATA = "data";
    private PacsOrder         mPacsorder;
    private PacsFtpAdapter    mAdapter;
    private List<DcmName>     mCurrentDcmNames;
    private int               mSelectImage;
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
        super.init();
        mPacsorder = getIntent().getParcelableExtra(KEY_DATA);
        mAdapter = new PacsFtpAdapter(R.layout.item_pacs_ftp, this);
    }


    @Override
    protected void initView() {
        mPicturePlayerView = mDataBinding.player;
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getPacsImages(mPacsorder.TStudyNo, ReportFactory.getInstance(mPacsorder).openImage(mPacsorder));
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                queryDcmFile(position);
                setRecyclerViewMark(position);
                showImageAndDown();
                initPlayerResource();
            }
        });

        mDataBinding.touch.setOnTouchListener(this);

        mPicturePlayerView.setOnUpdateListener(new OnUpdateListener() {
            @Override
            public void onUpdate(int frameIndex) {
                mSelectImage = frameIndex;
                changText(frameIndex);
            }
        });

        mPicturePlayerView.setOnStopListener(new OnStopListener() {
            @Override
            public void onStop() {
                changedPacsImage();
            }
        });
    }


    private void initPlayerResource() {
        String[] pathArray = new String[mCurrentDcmNames.size()];
        for (int i = 0; i < mCurrentDcmNames.size(); i++) {
            DcmName dcmName = mCurrentDcmNames.get(i);
            String imagename = dcmName.IMAGENAME;
            File pngFile = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename.replace(".dcm", ".png"));
            pathArray[i] = pngFile.getAbsolutePath();
        }
        mPicturePlayerView.stop();
        mPicturePlayerView.setDataSource(pathArray, pathArray.length * 80);
    }

    private void showImageAndDown() {
        int count = 0;
        for (int i = 0; i < mCurrentDcmNames.size(); i++) {
            DcmName dcmName = mCurrentDcmNames.get(i);
            String imagePath = dcmName.IMAGEPATH;
            String imagename = dcmName.IMAGENAME;
            File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, imagename);
            if (file.exists()) {
                if (i == 0) {
                    Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into(mDataBinding.container);
                    mDataBinding.container.setVisibility(View.VISIBLE);
                }
            } else {
                count++;
                showProgress();
                App.getAsynHandler().post(() -> FileDownUtil.downFileAndChangedPng(Contants.PACS_PATH + imagePath + imagename, file.getAbsolutePath(), null));
            }
        }
        mDataBinding.mark.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgress();
            }
        }, count * 50);
    }

    private void queryDcmFile(int position) {
        mSelectImage = position;
        PacsImagePath item = mAdapter.getItem(position);
        mCurrentDcmNames = new Select().from(DcmName.class).where(DcmName_Table.IMAGEPATH.eq(item.IMAGEPATH)).queryList();
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

    private void setRecyclerViewMark(int position) {
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
        return new PacsImagesPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPicturePlayerView.release();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if ((mCurrentDcmNames == null) || (mCurrentDcmNames.size() == 1)) {
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mPicturePlayerView.isPaused()) {
                    mPicturePlayerView.resume();
                } else {
                    mPicturePlayerView.start();
                }
                mDataBinding.container.setVisibility(View.INVISIBLE);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mPicturePlayerView.isPlaying()) {
                    mPicturePlayerView.pause();
                    changedPacsImage();
                }
                mDataBinding.container.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }
}
