package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.SpanUtils;
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
        SpanUtils sb = new SpanUtils();
        if (!StringUtils.isTrimEmpty(item.ArcimDesc)) {
            String[] hebalOrders = item.ArcimDesc.split(",");
           for (int i = 0; i < hebalOrders.length; i++) {
                String hebalOrder = hebalOrders[i];
                sb.append(hebalOrder);
                if (i != 0 && (i + 1) % 4 == 0) {
                    sb.appendLine();
                } else {
                    for (int j = 0; j < 2; j++) {
                        sb.append("\u3000");
                    }
                }
            }
        }
        helper.setText(R.id.order_name, sb.create())
                .setText(R.id.title, item.OrdCreateDate)
                .setText(R.id.order_desc, item.RecipeInfo);
    }
}
