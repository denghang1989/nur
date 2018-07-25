package szszhospital.cn.com.mobilenurse.factory.report;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PascClinicSetting;
import szszhospital.cn.com.mobilenurse.utils.GenericCode;


/**
 * DTXD-动态心电图室
 */
public class DTXDReport implements ReportUrl {

    public static final int LENGTH = 8;

    @Override
    public String getReportUrl(PascClinicSetting pascClinicSetting, PacsOrder pacsOrder) {
        String url = "";
        String data = pascClinicSetting.Data;
        String[] split = data.split("\\^");
        String reportFullFil = split[0];
        if (!reportFullFil.contains(".exe") && reportFullFil.startsWith("http")) {
            if (pacsOrder.TRegNo.trim().length() < LENGTH) {
                url = reportFullFil + GenericCode.autoGenericCode(pacsOrder.TRegNo, LENGTH);
            } else {
                url = reportFullFil + pacsOrder.TRegNo;
            }
        }
        return url;
    }
}
