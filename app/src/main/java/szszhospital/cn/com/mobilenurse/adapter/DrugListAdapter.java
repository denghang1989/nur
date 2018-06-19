package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

/**
 * 2018/5/19 16  ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugListAdapter extends BaseQuickAdapter<DispDetailResponse, BaseViewHolder> {

    public DrugListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DispDetailResponse item) {
        helper.setText(R.id.drugName, item.InciDesc)
                .setText(R.id.dispQty, "数量：" + item.DispQty + "   规格：" + item.Spec)
                .setText(R.id.InciCode, "药品编码：" + item.InciCode)
                .setText(R.id.dateTime, "用药时间：" + item.DosDateTime);
        switch (item.ConFirmFlag) {
            case "":
                helper.setVisible(R.id.icon_flag, false);
                break;
            case "A":
                helper.setVisible(R.id.icon_flag, true);
                break;
        }
       /* // 发药机发药的标识
        switch (item.DrugMechineFlag) {
            case "0": //发药机不能发药
                helper.setBackgroundColor(R.id.background,Color.parseColor("#00000000"));
                break;
            case "1": //发药机可以发药
                helper.setBackgroundColor(R.id.background, Color.parseColor("#F2F6FC"));
                break;
        }*/
    }
}
