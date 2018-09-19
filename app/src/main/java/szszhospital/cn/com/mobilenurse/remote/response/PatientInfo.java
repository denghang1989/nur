package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.TimeUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class PatientInfo implements Parcelable, Serializable, Comparable<PatientInfo> {

    /**
     * Age : 27岁
     * Diagnose : 2型糖尿病
     * DisBed : 0002
     * Doctor : doctor
     * EpisodeID : 12528188
     * MedicareNo : 203946
     * PAPMIName : 测试一
     * PaAdmDateTime : 2017-01-19 09:14
     * PatientID : 2920389
     * PayType : 医保
     * Sex : 男
     */

    public String Age;
    public String Diagnose;
    public String DisBed;
    public String Doctor;
    public String EpisodeID;
    public String MedicareNo;
    public String PAPMIName;
    public String PaAdmDateTime;
    public String PatientID;
    public String PayType;
    public String Sex;
    /**
     * PatientNo : 00879448
     */

    public String PatientNo;
    /**
     * MedicareNo : 4888
     * PatientID : 1
     * PatientNo : 1
     * Type : H
     */

    public String Type;
    /**
     * Loc :
     * MedicareNo : 10007
     * PatientID : 4
     * PatientNo : 4
     */

    public String Loc;
    /**
     * Days : 4
     * MedicareNo : 170501
     * PatientID : 113253
     * PatientNo : 138952
     */

    public String Days;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Age);
        dest.writeString(this.Diagnose);
        dest.writeString(this.DisBed);
        dest.writeString(this.Doctor);
        dest.writeString(this.EpisodeID);
        dest.writeString(this.MedicareNo);
        dest.writeString(this.PAPMIName);
        dest.writeString(this.PaAdmDateTime);
        dest.writeString(this.PatientID);
        dest.writeString(this.PayType);
        dest.writeString(this.Sex);
        dest.writeString(this.PatientNo);
        dest.writeString(this.Type);
        dest.writeString(this.Loc);
        dest.writeString(this.Days);
    }

    public PatientInfo() {
    }

    protected PatientInfo(Parcel in) {
        this.Age = in.readString();
        this.Diagnose = in.readString();
        this.DisBed = in.readString();
        this.Doctor = in.readString();
        this.EpisodeID = in.readString();
        this.MedicareNo = in.readString();
        this.PAPMIName = in.readString();
        this.PaAdmDateTime = in.readString();
        this.PatientID = in.readString();
        this.PayType = in.readString();
        this.Sex = in.readString();
        this.PatientNo = in.readString();
        this.Type = in.readString();
        this.Loc = in.readString();
        this.Days = in.readString();
    }

    public static final Creator<PatientInfo> CREATOR = new Creator<PatientInfo>() {
        @Override
        public PatientInfo createFromParcel(Parcel source) {
            return new PatientInfo(source);
        }

        @Override
        public PatientInfo[] newArray(int size) {
            return new PatientInfo[size];
        }
    };

    @Override
    public String toString() {
        return "PatientInfo{" +
                "Age='" + Age + '\'' +
                ", Diagnose='" + Diagnose + '\'' +
                ", DisBed='" + DisBed + '\'' +
                ", Doctor='" + Doctor + '\'' +
                ", EpisodeID='" + EpisodeID + '\'' +
                ", MedicareNo='" + MedicareNo + '\'' +
                ", PAPMIName='" + PAPMIName + '\'' +
                ", PaAdmDateTime='" + PaAdmDateTime + '\'' +
                ", PatientID='" + PatientID + '\'' +
                ", PayType='" + PayType + '\'' +
                ", Sex='" + Sex + '\'' +
                ", PatientNo='" + PatientNo + '\'' +
                ", Type='" + Type + '\'' +
                ", Loc='" + Loc + '\'' +
                ", Days='" + Days + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull PatientInfo o) {
        long o1Time = TimeUtils.string2Millis(PaAdmDateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        long o2Time = TimeUtils.string2Millis(o.PaAdmDateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        if (o1Time > o2Time) {
            return -1;
        } else {
            return 1;
        }
    }
}
