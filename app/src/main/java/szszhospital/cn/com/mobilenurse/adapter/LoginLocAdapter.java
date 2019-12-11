package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfo;

public class LoginLocAdapter extends BaseQuickAdapter<LocInfo, BaseViewHolder> {

    public LoginLocAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocInfo item) {
        helper.setText(R.id.item,item.locName);
    }
}
