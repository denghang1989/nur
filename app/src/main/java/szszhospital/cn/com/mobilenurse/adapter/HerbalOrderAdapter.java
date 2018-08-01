package szszhospital.cn.com.mobilenurse.adapter;

import android.util.Log;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
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
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isTrimEmpty(item.ArcimDesc)) {
            String[] hebalOrders = item.ArcimDesc.split(",");
            Optional<String> maxOrder = Stream.of(hebalOrders).max((o1, o2) -> o1.length() - o2.length());
            Log.d(TAG, "convert: " + maxOrder.get().length());
            for (int i = 0; i < hebalOrders.length; i++) {
                String hebalOrder = hebalOrders[i];
                sb.append(hebalOrder);
                int length = maxOrder.get().length() - hebalOrder.length();
                for (int j = 0; j < length; j++) {
                    sb.append(" ");
                }
                if (i != 0 && (i + 1) % 4 == 0) {
                    sb.append("\n");
                } else {
                    sb.append("    ");
                }
            }
        }
        helper.setText(R.id.order_name, sb.toString())
                .setText(R.id.title, item.OrdCreateDate)
                .setText(R.id.order_desc, item.RecipeInfo);
    }
}
