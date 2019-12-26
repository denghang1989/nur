package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.chrisbanes.photoview.PhotoView;

import szszhospital.cn.com.mobilenurse.R;

import static szszhospital.cn.com.mobilenurse.utils.Contants.EMR_KEY_PATH;
import static szszhospital.cn.com.mobilenurse.utils.Contants.PHOTO_PATH;


public class EMRImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public EMRImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String url) {
        PhotoView imageView = helper.getView(R.id.emr_image);
        String imagePath = SPUtils.getInstance().getString(EMR_KEY_PATH);
        String realPath = StringUtils.isEmpty(imagePath) ? PHOTO_PATH : imagePath;
        Glide.with(mContext).load(realPath + url).into(imageView);
        helper.addOnClickListener(R.id.emr_image);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
