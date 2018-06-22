package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class IPatientListAdapter extends BaseQuickAdapter<PatientInfo, BaseViewHolder> {
    public IPatientListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientInfo item) {
        helper.setText(R.id.patient_name, "姓名：" + item.PAPMIName)
                .setText(R.id.bedNo, "床号：" + item.DisBed);
    }
}
