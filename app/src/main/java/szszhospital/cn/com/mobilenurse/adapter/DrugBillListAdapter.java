package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 发药单列表
 * <p>
 * 1:  	^DHCSTPHACONFIG("PrintType",1)	= 	"KFY^1^0^^0^0^0"
 * 2:  	^DHCSTPHACONFIG("PrintType",2)	= 	"ZJ^0^1^^0^0^0"
 * 3:  	^DHCSTPHACONFIG("PrintType",3)	= 	"DSY^0^1^^0^0^0"
 * 4:  	^DHCSTPHACONFIG("PrintType",4)	= 	"DMY^1^0^^0^0^0"
 * 5:  	^DHCSTPHACONFIG("PrintType",5)	= 	"WYY^1^0^^0^0^0"
 * 6:  	^DHCSTPHACONFIG("PrintType",7)	= 	"ZCY^0^0^PrintZCY^0^0^0"
 * 7:  	^DHCSTPHACONFIG("PrintType",13)	= 	"GZY^1^0^^0^0^0"
 * 8:  	^DHCSTPHACONFIG("PrintType",14)	= 	"JY^1^0^^0^0^0"
 * 9:  	^DHCSTPHACONFIG("PrintType",15)	= 	"JE^1^0^^0^0^0"
 * 10:  	^DHCSTPHACONFIG("PrintType",16)	= 	"RCZZ^1^0^^0^0^0"
 * 11:  	^DHCSTPHACONFIG("PrintType",17)	= 	"YNZJ^1^0^^0^0^0"
 * 12:  	^DHCSTPHACONFIG("PrintType",18)	= 	"DF^1^0^^0^0^0"
 * 13:  	^DHCSTPHACONFIG("PrintType",19)	= 	"OUT^1^0^^0^0^0"
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
        switch (item.DispTypeCode) {
            case "KFY": //口服药
                helper.setImageResource(R.id.icon, R.drawable.icon_drug_eat);
                break;
            case "ZJ":
                break;
            case "DSY":
                break;
            case "DMY":
                break;
            case "WYY": //外用药
                helper.setImageResource(R.id.icon, R.drawable.icon_band_aid);
                break;
            case "ZCY":
                break;
            case "GZY":
                break;
            case "JY":
                break;
            case "JE":
                break;
            case "RCZZ":
                break;
            case "YNZJ":
                break;
            case "DF":
                break;
            case "OUT":
                break;
        }
    }
}
