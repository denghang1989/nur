package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Order implements Parcelable, Serializable {

    /**
     * ArcimDesc : 血常规
     * Doctor : 郭良
     * DoseQty :
     * DoseUnit :
     * Dura :
     * Instr :
     * LabEpisodeNo : 7727061
     * OEItemID : 15194639||7
     * OEORIPhQty : 1
     * OEORIQty :
     * OrdAction :
     * OrdBilled : TB
     * OrdCreateDate : 2018-05-07
     * OrdCreateTime : 16:49
     * OrdDepProcNotes :
     * OrdLabSpec : 全血(EDTA)
     * OrdSkinTest : N
     * OrdSkinTestResult :
     * OrdStartDate : 2018-05-07
     * OrdStartTime : 16:49
     * OrdStatus : 执行
     * OrdXDate :
     * OrdXTime :
     * PHFreq :
     * PackUOMDesc : 项
     * PatientID : 3378288
     * PrescNo :
     * Priority : 临时医嘱
     * QtyPackUOM :
     * RecipeInfo :
     * Reflag :
     * SeqNo : 1
     * dstatus :
     */

    public String ArcimDesc;
    public String Doctor;
    public String DoseQty;
    public String DoseUnit;
    public String Dura;
    public String Instr;
    public String LabEpisodeNo;
    public String OEItemID;
    public String OEORIPhQty;
    public String OEORIQty;
    public String OrdAction;
    public String OrdBilled;
    public String OrdCreateDate;
    public String OrdCreateTime;
    public String OrdDepProcNotes;
    public String OrdLabSpec;
    public String OrdSkinTest;
    public String OrdSkinTestResult;
    public String OrdStartDate;
    public String OrdStartTime;
    public String OrdStatus;
    public String OrdXDate;
    public String OrdXTime;
    public String PHFreq;
    public String PackUOMDesc;
    public String PatientID;
    public String PrescNo;
    public String Priority;
    public String QtyPackUOM;
    public String RecipeInfo;
    public String Reflag;
    public String SeqNo;
    public String dstatus;
    /**
     * OrdStatusCode : V
     * OrdStopDate :
     * OrdStopTime :
     */

    public String OrdStatusCode;
    public String OrdStopDate;
    public String OrdStopTime;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ArcimDesc);
        dest.writeString(this.Doctor);
        dest.writeString(this.DoseQty);
        dest.writeString(this.DoseUnit);
        dest.writeString(this.Dura);
        dest.writeString(this.Instr);
        dest.writeString(this.LabEpisodeNo);
        dest.writeString(this.OEItemID);
        dest.writeString(this.OEORIPhQty);
        dest.writeString(this.OEORIQty);
        dest.writeString(this.OrdAction);
        dest.writeString(this.OrdBilled);
        dest.writeString(this.OrdCreateDate);
        dest.writeString(this.OrdCreateTime);
        dest.writeString(this.OrdDepProcNotes);
        dest.writeString(this.OrdLabSpec);
        dest.writeString(this.OrdSkinTest);
        dest.writeString(this.OrdSkinTestResult);
        dest.writeString(this.OrdStartDate);
        dest.writeString(this.OrdStartTime);
        dest.writeString(this.OrdStatus);
        dest.writeString(this.OrdXDate);
        dest.writeString(this.OrdXTime);
        dest.writeString(this.PHFreq);
        dest.writeString(this.PackUOMDesc);
        dest.writeString(this.PatientID);
        dest.writeString(this.PrescNo);
        dest.writeString(this.Priority);
        dest.writeString(this.QtyPackUOM);
        dest.writeString(this.RecipeInfo);
        dest.writeString(this.Reflag);
        dest.writeString(this.SeqNo);
        dest.writeString(this.dstatus);
        dest.writeString(this.OrdStatusCode);
        dest.writeString(this.OrdStopDate);
        dest.writeString(this.OrdStopTime);
    }

    public Order() {
    }

    protected Order(Parcel in) {
        this.ArcimDesc = in.readString();
        this.Doctor = in.readString();
        this.DoseQty = in.readString();
        this.DoseUnit = in.readString();
        this.Dura = in.readString();
        this.Instr = in.readString();
        this.LabEpisodeNo = in.readString();
        this.OEItemID = in.readString();
        this.OEORIPhQty = in.readString();
        this.OEORIQty = in.readString();
        this.OrdAction = in.readString();
        this.OrdBilled = in.readString();
        this.OrdCreateDate = in.readString();
        this.OrdCreateTime = in.readString();
        this.OrdDepProcNotes = in.readString();
        this.OrdLabSpec = in.readString();
        this.OrdSkinTest = in.readString();
        this.OrdSkinTestResult = in.readString();
        this.OrdStartDate = in.readString();
        this.OrdStartTime = in.readString();
        this.OrdStatus = in.readString();
        this.OrdXDate = in.readString();
        this.OrdXTime = in.readString();
        this.PHFreq = in.readString();
        this.PackUOMDesc = in.readString();
        this.PatientID = in.readString();
        this.PrescNo = in.readString();
        this.Priority = in.readString();
        this.QtyPackUOM = in.readString();
        this.RecipeInfo = in.readString();
        this.Reflag = in.readString();
        this.SeqNo = in.readString();
        this.dstatus = in.readString();
        this.OrdStatusCode = in.readString();
        this.OrdStopDate = in.readString();
        this.OrdStopTime = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
