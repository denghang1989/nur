package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LisOrder implements Parcelable,Serializable{

    /**
     * AdmDate : 2018-05-07
     * AdmLoc : 外一科病区
     * AdmNo : 15202854
     * AdmType : 住院
     * AuthDateTime : 2018-05-21 09:54:41
     * HasMC : 0
     * HasMid : 0
     * LabEpisode : 7769173
     * LabTestSetRow : 7769173||H828||1
     * MajorConclusion :
     * OEOrdItemID : 15191790||454
     * OrdItemName : 急诊电解质4项
     * OrdSpecimen : 血清
     * PreReport :
     * PrintFlag : N
     * ReadFlag :
     * RecDateTime : 2018-05-21 09:08:42
     * ReceiveNotes :
     * ReqDateTime : 2018-05-20 09:54:52
     * ResultStatus : 3
     * SpecDateTime :
     * StatusDesc : 审核
     * TSMemo : 【审核】
     * TSResultAnomaly : 1
     * VisitNumberReportDR : 1972370
     * WarnComm :
     */

    public String AdmDate;
    public String AdmLoc;
    public String AdmNo;
    public String AdmType;
    public String AuthDateTime;
    public String HasMC;
    public String HasMid;
    public String LabEpisode;
    public String LabTestSetRow;
    public String MajorConclusion;
    public String OEOrdItemID;
    public String OrdItemName;
    public String OrdSpecimen;
    public String PreReport;
    public String PrintFlag;
    public String ReadFlag;
    public String RecDateTime;
    public String ReceiveNotes;
    public String ReqDateTime;
    public String ResultStatus;
    public String SpecDateTime;
    public String StatusDesc;
    public String TSMemo;
    public String TSResultAnomaly;
    public String VisitNumberReportDR;
    public String WarnComm;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AdmDate);
        dest.writeString(this.AdmLoc);
        dest.writeString(this.AdmNo);
        dest.writeString(this.AdmType);
        dest.writeString(this.AuthDateTime);
        dest.writeString(this.HasMC);
        dest.writeString(this.HasMid);
        dest.writeString(this.LabEpisode);
        dest.writeString(this.LabTestSetRow);
        dest.writeString(this.MajorConclusion);
        dest.writeString(this.OEOrdItemID);
        dest.writeString(this.OrdItemName);
        dest.writeString(this.OrdSpecimen);
        dest.writeString(this.PreReport);
        dest.writeString(this.PrintFlag);
        dest.writeString(this.ReadFlag);
        dest.writeString(this.RecDateTime);
        dest.writeString(this.ReceiveNotes);
        dest.writeString(this.ReqDateTime);
        dest.writeString(this.ResultStatus);
        dest.writeString(this.SpecDateTime);
        dest.writeString(this.StatusDesc);
        dest.writeString(this.TSMemo);
        dest.writeString(this.TSResultAnomaly);
        dest.writeString(this.VisitNumberReportDR);
        dest.writeString(this.WarnComm);
    }

    public LisOrder() {
    }

    protected LisOrder(Parcel in) {
        this.AdmDate = in.readString();
        this.AdmLoc = in.readString();
        this.AdmNo = in.readString();
        this.AdmType = in.readString();
        this.AuthDateTime = in.readString();
        this.HasMC = in.readString();
        this.HasMid = in.readString();
        this.LabEpisode = in.readString();
        this.LabTestSetRow = in.readString();
        this.MajorConclusion = in.readString();
        this.OEOrdItemID = in.readString();
        this.OrdItemName = in.readString();
        this.OrdSpecimen = in.readString();
        this.PreReport = in.readString();
        this.PrintFlag = in.readString();
        this.ReadFlag = in.readString();
        this.RecDateTime = in.readString();
        this.ReceiveNotes = in.readString();
        this.ReqDateTime = in.readString();
        this.ResultStatus = in.readString();
        this.SpecDateTime = in.readString();
        this.StatusDesc = in.readString();
        this.TSMemo = in.readString();
        this.TSResultAnomaly = in.readString();
        this.VisitNumberReportDR = in.readString();
        this.WarnComm = in.readString();
    }

    public static final Creator<LisOrder> CREATOR = new Creator<LisOrder>() {
        @Override
        public LisOrder createFromParcel(Parcel source) {
            return new LisOrder(source);
        }

        @Override
        public LisOrder[] newArray(int size) {
            return new LisOrder[size];
        }
    };
}
