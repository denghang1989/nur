package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.adeel.library.easyFTP;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsImagesBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsImagesContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsImagesPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsImagesActivity extends BasePresentActivity<ActivityPacsImagesBinding,PacsImagesPresenter> implements PacsImagesContract.View{

    private static final String KEY_DATA = "data";
    private PacsOrder mPacsorder;
    private easyFTP mFtp;

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
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsImages(mPacsorder.TStudyNo,"1");
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
    public void getRealImagePath(PacsImagePath pacsImagePath) {
        if (mFtp.getFtpClient().isConnected()) {
        } else {
        }
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
