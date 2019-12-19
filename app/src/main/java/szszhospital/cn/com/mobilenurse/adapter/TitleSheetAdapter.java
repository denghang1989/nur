package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

public class TitleSheetAdapter extends BaseQuickAdapter<LocAccess,BaseViewHolder> {
    public TitleSheetAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocAccess item) {
        helper.setText(R.id.title,item.Title);
    }
}
