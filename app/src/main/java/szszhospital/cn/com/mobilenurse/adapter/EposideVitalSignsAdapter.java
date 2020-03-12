package szszhospital.cn.com.mobilenurse.adapter;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.chrisbanes.photoview.PhotoView;

import androidx.annotation.NonNull;
import szszhospital.cn.com.mobilenurse.R;

public class EposideVitalSignsAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public EposideVitalSignsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        PhotoView imageView = helper.getView(R.id.emr_image);
        Glide.with(mContext).load(item).into(imageView);
    }
}
