package szszhospital.cn.com.mobilenurse.viewholder;

import android.content.Context;

import szszhospital.cn.com.mobilenurse.R;

public class TestFooterViewHolder extends BaseViewHolder<String> {

    public TestFooterViewHolder(Context context, int id) {
        super(context, id);
    }

    @Override
    protected void refreshViewByData() {
        setText(R.id.count, mData);
    }
}
