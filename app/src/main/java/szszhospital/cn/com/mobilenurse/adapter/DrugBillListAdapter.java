package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

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

    }
}
