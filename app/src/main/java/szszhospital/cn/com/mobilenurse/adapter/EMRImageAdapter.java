package szszhospital.cn.com.mobilenurse.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;

import static szszhospital.cn.com.mobilenurse.utils.Contants.PHOTO_PATH;

public class EMRImageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public EMRImageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String url) {
        ImageView imageView = helper.getView(R.id.emr_image);
        Glide.with(mContext).load(PHOTO_PATH + url).into(imageView);
        helper.addOnClickListener(R.id.emr_image);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
