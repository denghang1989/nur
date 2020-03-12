package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LisOrderDetail implements Serializable,Parcelable{


    /**
     * AbFlag :
     * AuthDate : 2018-06-24
     * AuthTime : 10:20:07
     * ClinicalSignifyS :
     * ExtraRes :
     * HelpDisInfo :
     * MultipleResistant :
     * PreAbFlag : L
     * PreResult : 2.56
     * PreResultDR : 24180469
     * RefRanges : 3.50-9.50
     * ReportDR : 2141573
     * ReportResultDR : 24221749
     * ResClass :
     * ResNoes :
     * Result : 6.88
     * ResultFormat : N
     * Synonym : WBC
     * TestCodeDR : 1728
     * TestCodeName : 白细胞[WBC]
     * Units : 10~9/L
     */

    public String AbFlag;
    public String AuthDate;
    public String AuthTime;
    public String ClinicalSignifyS;
    public String ExtraRes;
    public String HelpDisInfo;
    public String MultipleResistant;
    public String PreAbFlag;
    public String PreResult;
    public String PreResultDR;
    public String RefRanges;
    public String ReportDR;
    public String ReportResultDR;
    public String ResClass;
    public String ResNoes;
    public String Result;
    public String ResultFormat;
    public String Synonym;
    public String TestCodeDR;
    public String TestCodeName;
    public String Units;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AbFlag);
        dest.writeString(this.AuthDate);
        dest.writeString(this.AuthTime);
        dest.writeString(this.ClinicalSignifyS);
        dest.writeString(this.ExtraRes);
        dest.writeString(this.HelpDisInfo);
        dest.writeString(this.MultipleResistant);
        dest.writeString(this.PreAbFlag);
        dest.writeString(this.PreResult);
        dest.writeString(this.PreResultDR);
        dest.writeString(this.RefRanges);
        dest.writeString(this.ReportDR);
        dest.writeString(this.ReportResultDR);
        dest.writeString(this.ResClass);
        dest.writeString(this.ResNoes);
        dest.writeString(this.Result);
        dest.writeString(this.ResultFormat);
        dest.writeString(this.Synonym);
        dest.writeString(this.TestCodeDR);
        dest.writeString(this.TestCodeName);
        dest.writeString(this.Units);
    }

    public LisOrderDetail() {
    }

    protected LisOrderDetail(Parcel in) {
        this.AbFlag = in.readString();
        this.AuthDate = in.readString();
        this.AuthTime = in.readString();
        this.ClinicalSignifyS = in.readString();
        this.ExtraRes = in.readString();
        this.HelpDisInfo = in.readString();
        this.MultipleResistant = in.readString();
        this.PreAbFlag = in.readString();
        this.PreResult = in.readString();
        this.PreResultDR = in.readString();
        this.RefRanges = in.readString();
        this.ReportDR = in.readString();
        this.ReportResultDR = in.readString();
        this.ResClass = in.readString();
        this.ResNoes = in.readString();
        this.Result = in.readString();
        this.ResultFormat = in.readString();
        this.Synonym = in.readString();
        this.TestCodeDR = in.readString();
        this.TestCodeName = in.readString();
        this.Units = in.readString();
    }

    public static final Creator<LisOrderDetail> CREATOR = new Creator<LisOrderDetail>() {
        @Override
        public LisOrderDetail createFromParcel(Parcel source) {
            return new LisOrderDetail(source);
        }

        @Override
        public LisOrderDetail[] newArray(int size) {
            return new LisOrderDetail[size];
        }
    };
}
