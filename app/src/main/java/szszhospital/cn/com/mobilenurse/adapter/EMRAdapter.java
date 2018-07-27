package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;

public class EMRAdapter extends BaseQuickAdapter<EMREposideInfo, BaseViewHolder> {

    private int selectPosition;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public EMRAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EMREposideInfo item) {
        helper.setText(R.id.record, item.CategoryName == null ? "" : item.CategoryName);
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
