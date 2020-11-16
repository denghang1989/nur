package szszhospital.cn.com.mobilenurse.remote.response;

import java.io.Serializable;

public class PacsOrderItem implements Serializable {

    /**
     * DateTime : 2018-03-05
     * OrdItemId : 2463562||6
     * PdfPath : http://192.168.199.47:82/xds/index.php?appuser=1&type=HISCODE1&value=0001262822
     * ReplocDr : 65
     * ReportLocName : CSK-超声科
     * StudyNo : 180305704
     * ReportType : url
     * "PISFTPPath": "ftp^192.168.199.63^21^PIS^BAFY@2015^5"
     * OrderName : 胎儿生物物理评分(单项检查)
     */

    public String DateTime;
    public String OrdItemId;
    public String PdfPath;
    public String ReplocDr;
    public String ReportLocName;
    public String StudyNo;
    public String OrderName;
    public String ReportType;
    public String FTPPath;

}
