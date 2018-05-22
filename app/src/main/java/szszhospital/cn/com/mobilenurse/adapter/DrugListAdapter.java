package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

/**
 * 2018/5/19 16
 */
public class DrugListAdapter extends BaseQuickAdapter<DispDetailResponse, BaseViewHolder> {

    public DrugListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DispDetailResponse item) {
        helper.setText(R.id.drugName, item.InciDesc)
                .setText(R.id.dispQty, item.Spec + "   " + item.DispQty)
                .setText(R.id.dispAuditStatusDesc, item.ConFirmFlag);
        switch (item.ConFirmFlag) {
            case "A":
                break;
            case "N":
                break;
        }
    }
}
