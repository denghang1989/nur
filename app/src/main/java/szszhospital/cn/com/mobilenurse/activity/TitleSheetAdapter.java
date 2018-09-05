package szszhospital.cn.com.mobilenurse.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;

public class TitleSheetAdapter extends BaseQuickAdapter<LocAccessResponse,BaseViewHolder> {
    public TitleSheetAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocAccessResponse item) {
        helper.setText(R.id.title,item.Title);
    }
}
