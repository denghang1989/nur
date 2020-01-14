package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.EMRNavigation;

public class EMRNavigationAdapter extends BaseQuickAdapter<EMRNavigation, BaseViewHolder> {

    private int selectPosition;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public EMRNavigationAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMRNavigation item) {
        helper.setText(R.id.record, item.ItemTitle == null ? "" : item.ItemTitle);
        int position = helper.getAdapterPosition();
        if (position == selectPosition) {
            helper.setVisible(R.id.icon_flag, true);
            helper.setBackgroundRes(R.id.patient, R.color.white);
        } else {
            helper.setVisible(R.id.icon_flag, false);
            helper.setBackgroundRes(R.id.patient, R.color.transparent);
        }
    }
}
