package szszhospital.cn.com.mobilenurse.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 发药单列表
 * 1	KFY	口服药	KFY	0
 * 2	ZJ	针剂	ZJ	0
 * 3	DSY	大输液	DSY	0
 * 4	DMY	毒麻药	DMY	0
 * 5	WYY	外用药	WYY	0
 * 7	ZCY	中草药	ZCY	0
 * 13	GZY	贵重药	GZY	0
 * 14	JY	一类精神药物	JY	0
 * 15	JE	二类精神药物	JE	0
 * 16	RCZZ	妊娠终止药物	RCZZ	0
 * 17	YNZJ	院内制剂	YNZJ	0
 * 18	DF	代发耗材	DF	0
 * 19	OUT	出院带药	OUT	0
 */
public class DrugBillListAdapter extends BaseQuickAdapter<DrugBill, BaseViewHolder> {

    public DrugBillListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugBill item) {
        helper.setText(R.id.dispNo, "单号：" + item.DispNo)
                .setText(R.id.WardDesc, "病区：" + item.WardDesc)
                .setText(R.id.dispType, item.DispType + "   " + item.DispDateTime + "   " + item.AuditDr);
        drugType(helper, item);

        switch (item.MachineFlag) {
            case "0": //发药机不能发药
                helper.setBackgroundColor(R.id.background, Color.parseColor("#00000000"));
                break;
            case "1": //发药机可以发药
                helper.setBackgroundColor(R.id.background, Color.parseColor("#90EE90"));
                helper.setImageResource(R.id.icon, R.drawable.icon_machine);
                break;
        }
    }

    private void drugType(BaseViewHolder helper, DrugBill item) {
        switch (item.DispTypeCode) {
            case "KFY": //口服药
                helper.setImageResource(R.id.icon, R.drawable.icon_drug_eat);
                break;
            case "ZJ": //针剂
                helper.setImageResource(R.id.icon, R.drawable.icon_injection);
                break;
            case "DSY": //大输液
                helper.setImageResource(R.id.icon, R.drawable.icon_dripping);
                break;
            case "DMY": //毒麻药
                break;
            case "WYY": //外用药
                helper.setImageResource(R.id.icon, R.drawable.icon_band_aid);
                break;
            case "ZCY": //中草药
                helper.setImageResource(R.id.icon, R.drawable.icon_herbs);
                break;
            case "GZY": //贵重药
                break;
            case "JY": //一类精神药物
                break;
            case "JE": //二类精神药物
                break;
            case "RCZZ": //妊娠终止药物
                break;
            case "YNZJ": //院内制剂
                break;
            case "DF": //代发耗材
                break;
            case "OUT": //出院带药
                helper.setImageResource(R.id.icon, R.drawable.icon_out);
                break;
        }
    }
}
