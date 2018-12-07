package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

import szszhospital.cn.com.mobilenurse.entity.AppDatabase;

@Table(database = AppDatabase.class)
public class LisOrder extends BaseModel implements Parcelable,Serializable{


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

    @Column
    public String AdmDate;
    @Column
    public String AdmLoc;
    @Column
    public String AdmNo;
    @Column
    public String AdmType;
    @Column
    public String AuthDateTime;
    @Column
    public String HasMC;
    @Column
    public String HasMid;
    @Column
    public String LabEpisode;
    @Column
    public String LabTestSetRow;
    @Column
    public String MajorConclusion;
    @PrimaryKey
    @Column
    public String OEOrdItemID;
    @Column
    public String OrdItemName;
    @Column
    public String OrdSpecimen;
    @Column
    public String PreReport;
    @Column
    public String PrintFlag;
    @Column
    public String ReadFlag;
    @Column
    public String RecDateTime;
    @Column
    public String ReceiveNotes;
    @Column
    public String ReqDateTime;
    @Column
    public String ResultStatus;
    @Column
    public String SpecDateTime;
    @Column
    public String StatusDesc;
    @Column
    public String TSMemo;
    @Column
    public String TSResultAnomaly;
    @Column
    public String VisitNumberReportDR;
    @Column
    public String WarnComm;
    /**
     * AdmNo : 15467715
     * HasMC : 0
     * HasMid : 0
     * LabEpisode : 8119067
     * ResultStatus : 3
     * SortNum : 2018090740586
     * TSResultAnomaly : 0
     * Tips : /
     * VisitNumberReportDR : 2508026
     */

    public String SortNum;
    public String Tips;


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
