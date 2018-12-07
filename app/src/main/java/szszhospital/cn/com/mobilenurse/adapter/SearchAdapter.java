package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class SearchAdapter extends BaseQuickAdapter<PatientInfo, BaseViewHolder> {


    public SearchAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientInfo item) {
        helper.setText(R.id.patient_name, "姓名：" + item.PAPMIName)
                .setText(R.id.age, "年龄：" + item.Age)
                .setText(R.id.diagnose, "诊断：" + item.Diagnose)
                .setText(R.id.loc, "就诊科室：" + item.Loc)
                .setText(R.id.type, "就诊类型：" + item.Type);
        if (StringUtils.equals("男", item.Sex)) {
            helper.setImageResource(R.id.sex, R.drawable.icon_man);
        } else {
            helper.setImageResource(R.id.sex, R.drawable.icon_woman);
        }

        if (StringUtils.equals("I", item.Type)) {
            helper.setText(R.id.date, "入院日期：" + item.PaAdmDateTime + "-----住院天数：" + item.Days + " 天");
        } else {
            helper.setText(R.id.date, "就诊日期：" + item.PaAdmDateTime);
        }
    }
}
