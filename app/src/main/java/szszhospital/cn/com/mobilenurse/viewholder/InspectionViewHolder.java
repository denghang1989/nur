package szszhospital.cn.com.mobilenurse.viewholder;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionViewHolder extends BaseViewHolder<PacsOrderSubscribe> {

    public InspectionViewHolder(Context context, int id) {
        super(context, id);
    }

    @Override
    protected void refreshViewByData() {
        setText(R.id.name, "姓名：" + mData.PatName)
                .setText(R.id.loc, "就诊科室：" + mData.PatLoc)
                .setText(R.id.bedNo, "床号：" + mData.AdmBed)
                .setText(R.id.age, "年龄：" + mData.PatAge)
                .setText(R.id.number, "登记号：" + mData.PatNo)
                .setImageResource(R.id.sex, StringUtils.equals("男", mData.PatSex) ? R.drawable.icon_man : R.drawable.icon_woman);
        setVisible(R.id.sex, true);
    }
}
