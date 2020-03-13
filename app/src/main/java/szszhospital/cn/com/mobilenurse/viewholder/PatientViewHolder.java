package szszhospital.cn.com.mobilenurse.viewholder;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class PatientViewHolder extends BaseViewHolder<PatientInfo> {

    public PatientViewHolder(Context context, int id) {
        super(context, id);
    }

    @Override
    protected void refreshViewByData() {
        setVisible(R.id.icon, true);
        setText(R.id.name, "姓名：" + mData.PAPMIName)
                .setText(R.id.bed, mData.DisBed)
                .setText(R.id.patientId, "登记号：" + mData.PatientNo)
                .setText(R.id.dateTime, "入院日期：" + mData.PaAdmDateTime)
                .setText(R.id.diagnose, "诊断：" + mData.Diagnose)
                .setText(R.id.payType, "费用类型：" + mData.PayType);

        if (StringUtils.equals(mData.Sex, "男")) {
            setImageResource(R.id.icon, R.drawable.icon_man);
        } else {
            setImageResource(R.id.icon, R.drawable.icon_woman);
        }

    }
}
