package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 发药单列表
 */
public class DrugBillListAdapter extends BaseQuickAdapter<DrugBill, BaseViewHolder> {

    public DrugBillListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugBill item) {
        helper.setText(R.id.dispNo, "单号：" + item.DispNo)
                .setText(R.id.WardDesc, "病区：" + item.WardDesc)
                .setText(R.id.dispType, item.DispType + "   " + item.DispDateTime + "   " + item.AuditDr);
    }
}
