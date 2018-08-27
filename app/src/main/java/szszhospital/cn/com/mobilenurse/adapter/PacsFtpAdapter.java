package szszhospital.cn.com.mobilenurse.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.io.File;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.utils.Contants;
import szszhospital.cn.com.mobilenurse.utils.DcmUtil;
import szszhospital.cn.com.mobilenurse.utils.FileCallback;
import szszhospital.cn.com.mobilenurse.utils.FileDownUtil;
import szszhospital.cn.com.mobilenurse.utils.FtpUtil;

public class PacsFtpAdapter extends BaseQuickAdapter<PacsImagePath, BaseViewHolder> {

    private FtpUtil  mFtpUtil;
    private Activity mActivity;
    private int      mSelected;

    public PacsFtpAdapter(int layoutResId, FtpUtil ftpUtil, Activity activity) {
        super(layoutResId);
        mFtpUtil = ftpUtil;
        mActivity = activity;
    }

    public void setSelected(int selected) {
        mSelected = selected;
    }

    @Override
    protected void convert(BaseViewHolder helper, PacsImagePath item) {
        int position = helper.getAdapterPosition();
        if (position == mSelected) {
            helper.setVisible(R.id.tag, true);
        } else {
            helper.setVisible(R.id.tag, false);
        }
        ImageView imageView = helper.getView(R.id.pacs_image);
        String path = item.thumbnailPath;
        if (!StringUtils.isTrimEmpty(path)) {
            if (path.endsWith("dcm")) {
                //判断是否存在文件
                File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, item.name);
                if (file.exists()) {
                    //解析加载图片
                    Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into(imageView);
                } else {
                    //下载图片
                    App.getAsynHandler().post(() -> FileDownUtil.downFileAndChangedPng(Contants.PACS_PATH + path, file.getAbsolutePath(), new FileCallback() {
                        @Override
                        public void success(File file) {
                            mActivity.runOnUiThread(() -> Glide.with(App.mContext).load(file).into(imageView));
                        }

                        @Override
                        public void error(Exception e) {

                        }
                    }));
                }

            } else {
                Glide.with(App.mContext).load(Contants.PACS_PATH + path).into(imageView);
            }
        }
    }
}
