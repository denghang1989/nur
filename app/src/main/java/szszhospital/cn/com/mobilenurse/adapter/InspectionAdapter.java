package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

public class InspectionAdapter extends BaseQuickAdapter<PacsOrderSubscribe.OrderSubscribe, BaseViewHolder> {

    public InspectionAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PacsOrderSubscribe.OrderSubscribe item) {
        helper.setText(R.id.name, "项目：" + item.arcListData)
                .setText(R.id.dateTime, "申请时间：" + item.ReqData + "    " + item.ReqTime)
                .setText(R.id.loc, "接收科室：" + item.arExLocDesc);
    }

}
