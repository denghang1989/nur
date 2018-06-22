package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class IPatientListAdapter extends BaseQuickAdapter<PatientInfo, BaseViewHolder> {

    private int mSelected;

    public IPatientListAdapter(int layoutResId) {
        super(layoutResId);
    }

    public void setSelected(int selected) {
        mSelected = selected;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientInfo item) {
        helper.setText(R.id.patient_name, "姓名：" + item.PAPMIName)
                .setText(R.id.age, "年龄：" + item.Age)
                .setText(R.id.diagnose, "诊断：" + item.Diagnose)
                .setText(R.id.bedNo, item.DisBed);
        if (StringUtils.equals("男", item.Sex)) {
            helper.setImageResource(R.id.sex, R.drawable.icon_man);
        } else {
            helper.setImageResource(R.id.sex, R.drawable.icon_woman);
        }

        int position = helper.getAdapterPosition();
        if (position == mSelected) {
            helper.setVisible(R.id.select, true);
            helper.setBackgroundRes(R.id.background, R.drawable.bg_patient_select);
        } else {
            helper.setVisible(R.id.select, false);
            helper.setBackgroundRes(R.id.background, R.drawable.bg_patient_normal);
        }
    }
}
