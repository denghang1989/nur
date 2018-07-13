package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Test;

public class TestAdapter extends BaseQuickAdapter<Test, BaseViewHolder> {
    public TestAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Test item) {
        helper.setText(R.id.LabNo, "检验号:" + item.LabNo)
                .setText(R.id.patientNo, "登记号：" + item.PatientNo)
                .setText(R.id.name, item.PatientName + "  " + item.Age +"  "+ item.BedNo)
                .setText(R.id.loc, "就诊科室：" + item.LocDesc);
        switch (item.Status) {
            case "A":
                helper.setImageResource(R.id.icon, R.drawable.icon_test_receive);
                break;
            case "B":
                helper.setImageResource(R.id.icon, R.drawable.icon_test_delivery);
                break;
        }
    }
}
