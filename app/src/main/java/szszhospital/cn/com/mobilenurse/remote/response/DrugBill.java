package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * 发药单
 */
public class DrugBill implements Parcelable,Serializable{

    /**
     * AuditDr : 7
     * WardDesc : 风湿肿瘤与血液病科护士站
     * DispNo : ZXYF1805130001
     * DispType : 口服药
     * DispDateTime : 2018-05-13 15:19:31
     * DispUser : pharmacy
     */

    public String AuditDr;
    public String WardDesc;
    public String DispNo;
    public String DispType;
    public String DispDateTime;
    public String DispUser;


    /**
     * DispTypeCode : KFY
     */

    public String DispTypeCode;

    public String MachineFlag;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AuditDr);
        dest.writeString(this.WardDesc);
        dest.writeString(this.DispNo);
        dest.writeString(this.DispType);
        dest.writeString(this.DispDateTime);
        dest.writeString(this.DispUser);
        dest.writeString(this.DispTypeCode);
        dest.writeString(this.MachineFlag);
    }

    public DrugBill() {
    }

    protected DrugBill(Parcel in) {
        this.AuditDr = in.readString();
        this.WardDesc = in.readString();
        this.DispNo = in.readString();
        this.DispType = in.readString();
        this.DispDateTime = in.readString();
        this.DispUser = in.readString();
        this.DispTypeCode = in.readString();
        this.MachineFlag = in.readString();
    }

    public static final Creator<DrugBill> CREATOR = new Creator<DrugBill>() {
        @Override
        public DrugBill createFromParcel(Parcel source) {
            return new DrugBill(source);
        }

        @Override
        public DrugBill[] newArray(int size) {
            return new DrugBill[size];
        }
    };
}
