package szszhospital.cn.com.mobilenurse.remote.response;

public class PacsOrderItem {

    /**
     * DateTime : 2018-03-05
     * OrdItemId : 2463562||6
     * PdfPath : http://192.168.199.47:82/xds/index.php?appuser=1&type=HISCODE1&value=0001262822
     * ReplocDr : 65
     * ReportLocName : CSK-超声科
     * StudyNo : 180305704
     * ReportType : url
     * OrderName : 胎儿生物物理评分(单项检查)
     */

    private String DateTime;
    private String OrdItemId;
    private String PdfPath;
    private int    ReplocDr;
    private String ReportLocName;
    private int    StudyNo;
    private String OrderName;
    private String ReportType;

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String DateTime) {
        this.DateTime = DateTime;
    }

    public String getOrdItemId() {
        return OrdItemId;
    }

    public void setOrdItemId(String OrdItemId) {
        this.OrdItemId = OrdItemId;
    }

    public String getPdfPath() {
        return PdfPath;
    }

    public void setPdfPath(String PdfPath) {
        this.PdfPath = PdfPath;
    }

    public int getReplocDr() {
        return ReplocDr;
    }

    public void setReplocDr(int ReplocDr) {
        this.ReplocDr = ReplocDr;
    }

    public String getReportLocName() {
        return ReportLocName;
    }

    public void setReportLocName(String ReportLocName) {
        this.ReportLocName = ReportLocName;
    }

    public int getStudyNo() {
        return StudyNo;
    }

    public void setStudyNo(int StudyNo) {
        this.StudyNo = StudyNo;
    }

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String OrderName) {
        this.OrderName = OrderName;
    }

    public String getReportType() {
        return ReportType;
    }

    public void setReportType(String ReportType) {
        this.ReportType = ReportType;
    }
}
