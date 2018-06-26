package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class HerbalOrderAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public HerbalOrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        String result = "";
        if (!StringUtils.isTrimEmpty(item.ArcimDesc)) {
            String[] hebalOrders = item.ArcimDesc.split(",");
            for (int i = 0; i < hebalOrders.length; i++) {
                if (i == 0) {
                    result = hebalOrders[i];
                } else {
                    if (i % 4 == 0) {
                        result = result + "\n" + hebalOrders[i];
                    } else {
                        result = result + "    " + hebalOrders[i];
                    }
                }
            }
        }
        helper.setText(R.id.order_name, result)
                .setText(R.id.title,item.OrdCreateDate+" "+item.OrdStartTime)
                .setText(R.id.order_desc,item.RecipeInfo);
    }
}
