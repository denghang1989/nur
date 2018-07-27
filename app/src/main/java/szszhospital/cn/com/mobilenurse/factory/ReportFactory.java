package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.factory.report.BLKReport;
import szszhospital.cn.com.mobilenurse.factory.report.CSYXReport;
import szszhospital.cn.com.mobilenurse.factory.report.CTSReport;
import szszhospital.cn.com.mobilenurse.factory.report.DTXDReport;
import szszhospital.cn.com.mobilenurse.factory.report.FSYXKReport;
import szszhospital.cn.com.mobilenurse.factory.report.JDTSReport;
import szszhospital.cn.com.mobilenurse.factory.report.JLDPReport;
import szszhospital.cn.com.mobilenurse.factory.report.JRSReport;
import szszhospital.cn.com.mobilenurse.factory.report.NDTSReport;
import szszhospital.cn.com.mobilenurse.factory.report.NKJSReport;
import szszhospital.cn.com.mobilenurse.factory.report.WebViewReportHandler;
import szszhospital.cn.com.mobilenurse.factory.report.XDTSReport;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

/**
 * goable
 * 1:  	^DHCRBCi("LocClinicSet",9,2)	= 	""
 * 2:  	^DHCRBCi("LocClinicSet",11,1)	= 	""
 * 3:  	^DHCRBCi("LocClinicSet",12,13)	= 	""
 * 4:  	^DHCRBCi("LocClinicSet",13,5)	= 	""
 * 5:  	^DHCRBCi("LocClinicSet",14,3)	= 	""
 * 6:  	^DHCRBCi("LocClinicSet",15,6)	= 	""
 * 7:  	^DHCRBCi("LocClinicSet",16,8)	= 	""
 * 8:  	^DHCRBCi("LocClinicSet",17,9)	= 	""
 * 9:  	^DHCRBCi("LocClinicSet",77,4)	= 	""
 * 10:  ^DHCRBCi("LocClinicSet",112,12)	= 	""
 * 11:  ^DHCRBCi("LocClinicSet",164,10)	= 	""
 * 12:  ^DHCRBCi("LocClinicSet",165,11)	= 	""
 */
public class ReportFactory {

    public static WebViewReportHandler getInstance(PacsOrder pacsOrder) {
        String treplocDr = pacsOrder.TreplocDr;
        WebViewReportHandler handler = null;
        switch (treplocDr) {
            case "9": //FSYXK-放射影像科
                handler = new FSYXKReport();
                break;
            case "11": //CTS-CT室
                handler = new CTSReport();
                break;
            case "12": //JRS-介入室
                handler = new JRSReport();
                break;
            case "13": //BLK-病理科
                handler = new BLKReport();
                break;
            case "14": //CSYX-超声影像科
                handler = new CSYXReport();
                break;
            case "15": //XDTS-心电图室
                handler = new XDTSReport();
                break;
            case "16": //DTXD-动态心电图室
                handler = new DTXDReport();
                break;
            case "17": //JLDP-经颅多普勒室
                handler = new JLDPReport();
                break;
            case "77": //NKJS-内窥镜室
                handler = new NKJSReport();
                break;
            case "112": //YMZB-一门诊B超室
                break;
            case "164": //NDTS-脑电图室
                handler = new NDTSReport();
                break;
            case "165": //JDTS-肌电图室
                handler = new JDTSReport();
                break;
            default:
                break;
        }
        return handler;
    }

}
