package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;

public class PacsOrderItemAdapter extends BaseQuickAdapter<PacsOrderItem, BaseViewHolder> {

    public PacsOrderItemAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PacsOrderItem item) {
        helper.addOnClickListener(R.id.icon).addOnClickListener(R.id.photo);
        helper.setText(R.id.dateTime,item.DateTime)
                .setText(R.id.name_order,item.OrderName)
                .setText(R.id.loc_order,item.ReportLocName);
    }


}
