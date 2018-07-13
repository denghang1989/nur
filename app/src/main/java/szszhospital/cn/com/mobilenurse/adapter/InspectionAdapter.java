package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionAdapter extends BaseQuickAdapter<PacsOrderSubscribe, BaseViewHolder> {

    public InspectionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PacsOrderSubscribe item) {
        helper.setText(R.id.name, "项目：" + item.arcListData)
                .setText(R.id.dateTime, "申请时间：" + item.ReqData + "    " + item.ReqTime)
                .setText(R.id.loc, "接收科室：" + item.arExLocDesc)
                .setText(R.id.type,"检查类型："+item.Type);
        switch (item.transportStatus) {
            case "":
                helper.setVisible(R.id.icon,false);
                break;
            case "A":
                helper.setVisible(R.id.icon,true);
                helper.setImageResource(R.id.icon,R.drawable.icon_leave_home);
                break;
            case "B":
                helper.setVisible(R.id.icon,true);
                helper.setImageResource(R.id.icon,R.drawable.icon_check);
                break;
            case "C":
                helper.setVisible(R.id.icon,true);
                helper.setImageResource(R.id.icon,R.drawable.icon_go_home);
                break;
        }
    }

}
