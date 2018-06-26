package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public class LisOrderListAdapter extends BaseQuickAdapter<LisOrder, BaseViewHolder> {

    public LisOrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LisOrder item) {
        helper.setText(R.id.lis_name, item.OrdItemName)
                .setText(R.id.dateTime, item.AuthDateTime)
                .setText(R.id.desc_flag, item.TSMemo);
        if (StringUtils.equals("1", item.TSResultAnomaly)) {
            helper.setImageResource(R.id.flag, R.drawable.icon_abnormal);
        } else if (StringUtils.equals("0", item.TSResultAnomaly)) {
            helper.setImageResource(R.id.flag, R.drawable.icon_normal);
        }

        if (!StringUtils.equals(item.ResultStatus,"3")) {
            helper.setVisible(R.id.flag, false);
        } else {
            helper.setVisible(R.id.flag, true);
        }
    }
}
