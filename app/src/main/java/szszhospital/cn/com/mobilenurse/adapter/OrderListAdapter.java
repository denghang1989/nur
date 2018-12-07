package szszhospital.cn.com.mobilenurse.adapter;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class OrderListAdapter extends BaseMultiItemQuickAdapter<Order, BaseViewHolder> {

    public OrderListAdapter(List<Order> data) {
        super(data);
        addItemType(Order.V_ORDER, R.layout.item_order);
        addItemType(Order.D_ORDER, R.layout.item_order_d);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        int itemViewType = helper.getItemViewType();
        switch (itemViewType) {
            case Order.V_ORDER:
                vOrderConvert(helper, item);
                break;
            case Order.D_ORDER:
                dOrderConvert(helper, item);
                break;
            default:
                break;
        }
    }

    private void dOrderConvert(BaseViewHolder helper, Order item) {
        String startTimeString = ChangedDateTime(item.OrdStartDate + " " + item.OrdStartTime);
        String arcimDesc = getArcimDesc(item);
        String stopTimeString = ChangedDateTime(item.OrdStopDate + " " + item.OrdStopTime);

        helper.setText(R.id.StartDateTime, startTimeString)
                .setText(R.id.doctor, item.Doctor)
                .setText(R.id.order_name, arcimDesc)
                .setText(R.id.stopDateTime, "DC-D " + stopTimeString)
                .setText(R.id.stopDoctor, item.OrdStopDoctor);

    }

    private void vOrderConvert(BaseViewHolder helper, Order item) {
        String startTimeString = ChangedDateTime(item.OrdStartDate + " " + item.OrdStartTime);
        String arcimDesc = getArcimDesc(item);

        helper.setText(R.id.StartDateTime, startTimeString)
                .setText(R.id.doctor, item.Doctor)
                .setText(R.id.order_name, arcimDesc);

    }

    @NonNull
    private String ChangedDateTime(String dateTime) {
        long millis = TimeUtils.string2Millis(dateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA));
        return TimeUtils.millis2String(millis, new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA));
    }

    private String getArcimDesc(Order item) {
        String arcimDesc = item.ArcimDesc;
        if (!StringUtils.isTrimEmpty(item.OrdDepProcNotes)) {
            arcimDesc = arcimDesc + " (" + item.OrdDepProcNotes + ")";
        }
        if (!StringUtils.isTrimEmpty(item.Instr)) {
            arcimDesc = arcimDesc + "   " + item.Instr;
        }
        if (!StringUtils.isTrimEmpty(item.PHFreq)) {
            arcimDesc = arcimDesc + "   " + item.PHFreq;
        }
        if (!StringUtils.isTrimEmpty(item.OEORIPhQty)) {
            arcimDesc = arcimDesc + "  " + item.OEORIPhQty + item.DoseUnit;
        }
        if (item.SeqNo.contains(".")) {
            arcimDesc = "__" + arcimDesc;
        }
        return arcimDesc;
    }
}
