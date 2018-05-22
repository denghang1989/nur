package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Drug;

/**
 * 2018/5/19 16
 */
public class DrugListAdapter extends BaseQuickAdapter<Drug, BaseViewHolder> {

    public DrugListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Drug item) {
        helper.setText(R.id.drugName, item.drugName)
                .setText(R.id.dispQty, item.dispQty + "   " + item.doseQty)
                .setText(R.id.dispAuditStatusDesc, item.dispAuditStatusDesc);
        switch (item.dispAuditStatus) {
            case "A":
                break;
            case "N":
                break;
        }
    }
}
