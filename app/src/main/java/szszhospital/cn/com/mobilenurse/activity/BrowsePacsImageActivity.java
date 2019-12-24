package szszhospital.cn.com.mobilenurse.activity;


import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.os.Parcelable;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityBrowsePacsBinding;
import szszhospital.cn.com.mobilenurse.dialog.ImagePathDialogFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.BrowsePacsImageContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.BrowsePacsImagePresenter;
import szszhospital.cn.com.mobilenurse.entity.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;
import szszhospital.cn.com.mobilenurse.view.imageplayer.ImagePlayer;
import szszhospital.cn.com.mobilenurse.view.imageplayer.OnRenderListener;

public class BrowsePacsImageActivity extends BasePresentActivity<ActivityBrowsePacsBinding, BrowsePacsImagePresenter> implements
        BrowsePacsImageContract.View, TextureView.SurfaceTextureListener,
        OnRenderListener, CheckBox.OnCheckedChangeListener,
        View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final String                  DATA = "data";
    private              PacsOrderItem           mPacsOrderItem;
    private              ImagePlayer             mImagePlayer;
    private              ImagePathDialogFragment mImagePathDialogFragment;

    public static void startBrowsePacsImageActivity(Context context, PacsOrderItem pacsOrderItem) {
        Intent intent = new Intent(context, BrowsePacsImageActivity.class);
        intent.putExtra(DATA, (Parcelable) pacsOrderItem);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mPacsOrderItem = getIntent().getParcelableExtra(DATA);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_browse_pacs;
    }

    @Override
    protected void initData() {
        mPresenter.getPacsImagePath(mPacsOrderItem.StudyNo, mPacsOrderItem.ReplocDr, mPacsOrderItem.OrdItemId);
    }

    @Override
    protected void initEvent() {
        mDataBinding.checkbox.setOnCheckedChangeListener(this);
        mDataBinding.back.setOnClickListener(this);
        mDataBinding.pre.setOnClickListener(this);
        mDataBinding.next.setOnClickListener(this);
        mDataBinding.seekBar.setOnSeekBarChangeListener(this);
        mDataBinding.menu.setOnClickListener(this);
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
    public void savePacsImagePath(List<PacsImagePath> pacsImagePaths) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mImagePlayer = new ImagePlayer(this, mDataBinding.textureView);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        surface.release();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onStartRender(int index) {

    }

    @Override
    public void onFinishRender(int index) {
        mDataBinding.playNumber.setText(index);
    }

    @Override
    public void onRenderError(String msg) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (mImagePlayer != null) {
                mImagePlayer.play();
            }
        } else {
            if (mImagePlayer != null) {
                mImagePlayer.stop();
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back:
                finish();
                break;
            case R.id.pre:
                if (mImagePlayer != null) {
                    mImagePlayer.next();
                }
                break;
            case R.id.next:
                if (mImagePlayer != null) {
                    mImagePlayer.prev();
                }
                break;
            case R.id.menu:
                openDialogFragment();
                break;
            default:
                break;
        }
    }

    private void openDialogFragment() {
        mImagePathDialogFragment = (ImagePathDialogFragment) getSupportFragmentManager().findFragmentByTag(ImagePathDialogFragment.Key);
        if (mImagePathDialogFragment != null) {
            mImagePathDialogFragment.dismiss();
        }
        mImagePathDialogFragment = ImagePathDialogFragment.getInstance();
        mImagePathDialogFragment.show(getSupportFragmentManager(), ImagePathDialogFragment.Key);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if ((fromUser) && (mImagePlayer != null)) {
            mImagePlayer.seekTo(progress);
        }
        mDataBinding.playNumber.setText(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
