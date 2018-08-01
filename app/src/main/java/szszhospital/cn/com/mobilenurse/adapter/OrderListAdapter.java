package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Locale;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class OrderListAdapter extends BaseQuickAdapter<Order, BaseViewHolder> {

    public OrderListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        long millis = TimeUtils.string2Millis(item.OrdStartDate + " " + item.OrdStartTime, new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA));
        String startTimeString = TimeUtils.millis2String(millis, new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA));
        helper.setText(R.id.StartDateTime, startTimeString)
                .setText(R.id.stopDateTime, "")
                .setText(R.id.doctor, item.Doctor);
        String arcimDesc = item.ArcimDesc;
        if (!StringUtils.isTrimEmpty(item.OrdDepProcNotes)) {
            arcimDesc = arcimDesc + " (" + item.OrdDepProcNotes + ")";
        }
        if (!StringUtils.isTrimEmpty(item.DoseQty)) {
            arcimDesc = arcimDesc + "  " + item.DoseQty + item.DoseUnit;
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
        helper.setText(R.id.order_name, arcimDesc);
    }
}
