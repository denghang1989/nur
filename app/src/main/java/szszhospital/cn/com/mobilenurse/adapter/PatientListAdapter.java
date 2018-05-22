package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.model.Patient;

public class PatientListAdapter extends BaseQuickAdapter<Patient, BaseViewHolder> {

    private int selectPosition;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        notifyDataSetChanged();
    }

    public PatientListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Patient item) {
        helper.setText(R.id.user_name, item.patName);
        int position = helper.getAdapterPosition();
        if (position == selectPosition) {
            helper.setVisible(R.id.select, true);
            helper.setBackgroundRes(R.id.patient, R.color.white);
        } else {
            helper.setVisible(R.id.select, false);
            helper.setBackgroundRes(R.id.patient, R.color.transparent);
        }
    }
}
