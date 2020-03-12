package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class PacsOrderItem implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.DateTime);
        dest.writeString(this.OrdItemId);
        dest.writeString(this.PdfPath);
        dest.writeString(this.ReplocDr);
        dest.writeString(this.ReportLocName);
        dest.writeString(this.StudyNo);
        dest.writeString(this.OrderName);
        dest.writeString(this.ReportType);
        dest.writeString(this.FTPPath);
    }

    public PacsOrderItem() {
    }

    protected PacsOrderItem(Parcel in) {
        this.DateTime = in.readString();
        this.OrdItemId = in.readString();
        this.PdfPath = in.readString();
        this.ReplocDr = in.readString();
        this.ReportLocName = in.readString();
        this.StudyNo = in.readString();
        this.OrderName = in.readString();
        this.ReportType = in.readString();
        this.FTPPath = in.readString();
    }

    public static final Creator<PacsOrderItem> CREATOR = new Creator<PacsOrderItem>() {
        @Override
        public PacsOrderItem createFromParcel(Parcel source) {
            return new PacsOrderItem(source);
        }

        @Override
        public PacsOrderItem[] newArray(int size) {
            return new PacsOrderItem[size];
        }
    };
}
