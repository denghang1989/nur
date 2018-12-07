package szszhospital.cn.com.mobilenurse.adapter;

import android.app.Activity;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.raizlabs.android.dbflow.sql.language.Select;

import org.dcm4che3.data.Tag;

import java.io.File;
import java.util.Map;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName;
import szszhospital.cn.com.mobilenurse.remote.response.DcmName_Table;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.utils.Contants;
import szszhospital.cn.com.mobilenurse.utils.DcmUtil;
import szszhospital.cn.com.mobilenurse.utils.FileCallback;
import szszhospital.cn.com.mobilenurse.utils.FileDownUtil;

public class PacsFtpAdapter extends BaseQuickAdapter<PacsImagePath, BaseViewHolder> {

    private Activity mActivity;
    private int mSelected = -1;

    public PacsFtpAdapter(int layoutResId, Activity activity) {
        super(layoutResId);
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
        String path = item.thumbnailPath;
        if (!StringUtils.isTrimEmpty(path)) {
            handleDcmFile(item, helper, path);
            ShowText(item, helper);
        }
    }

    private void ShowText(PacsImagePath item, BaseViewHolder helper) {
        //判断是否存在文件
        File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, item.name);
        if (file.exists()) {
            //解析图片
            Map<Integer, String> dcmTagInfo = DcmUtil.getDcmTagInfo(file.getAbsolutePath());
            long count = new Select().from(DcmName.class).where(DcmName_Table.IMAGEPATH.eq(item.IMAGEPATH)).queryList().size();
            helper.setText(R.id.date,dcmTagInfo.get(Tag.StudyDate))
                    .setText(R.id.info,String.valueOf(count));
        }
    }

    private void handleDcmFile(PacsImagePath item, BaseViewHolder helper, String path) {
        //判断是否存在文件
        File file = new File(Contants.PACS_DCM_DOWNLOAD_PATH, item.name);
        if (file.exists()) {
            //解析加载图片
            Glide.with(App.mContext).load(DcmUtil.readFile(file.getAbsolutePath())).into((ImageView) helper.getView(R.id.pacs_image));
        } else {
            //下载图片
            App.getAsynHandler().post(() -> FileDownUtil.downFileAndChangedPng(Contants.PACS_PATH + path, file.getAbsolutePath(), new FileCallback() {
                @Override
                public void success(File file) {
                    mActivity.runOnUiThread(() -> {
                        Glide.with(App.mContext).load(file).into((ImageView) helper.getView(R.id.pacs_image));
                        ShowText(item,helper);
                    });
                }

                @Override
                public void error(Exception e) {

                }
            }));
        }
    }
}
