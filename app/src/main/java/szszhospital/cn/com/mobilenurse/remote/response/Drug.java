package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Drug implements Parcelable, Serializable {

    /**
     * wardDesc : 呼吸病科护士站
     * bedCode : 11床床
     * patName : 杨国强
     * drugName : {市}复方甲氧那明胶囊[合资](60粒)
     * dispQty : 60 粒
     * doseQty : 2.000粒
     * regNo : 01498233
     * inciId : 4163
     * drugCode : 12302131
     * dispAuditId : 2||2
     * dispAuditStatus : N
     * dispAuditStatusDesc : 未发药
     * oeoreId : 14511587||239
     * prefDispStatus : N
     */

    public String wardDesc;
    public String bedCode;
    public String patName;
    public String drugName;
    public String dispQty;
    public String doseQty;
    public String regNo;
    public String inciId;
    public String drugCode;
    public String dispAuditId;
    public String dispAuditStatus;
    public String dispAuditStatusDesc;
    public String oeoreId;
    public String prefDispStatus;


    @Override
    public String toString() {
        return "Drug{" +
                "wardDesc='" + wardDesc + '\'' +
                ", bedCode='" + bedCode + '\'' +
                ", patName='" + patName + '\'' +
                ", drugName='" + drugName + '\'' +
                ", dispQty='" + dispQty + '\'' +
                ", doseQty='" + doseQty + '\'' +
                ", regNo='" + regNo + '\'' +
                ", inciId='" + inciId + '\'' +
                ", drugCode='" + drugCode + '\'' +
                ", dispAuditId='" + dispAuditId + '\'' +
                ", dispAuditStatus='" + dispAuditStatus + '\'' +
                ", dispAuditStatusDesc='" + dispAuditStatusDesc + '\'' +
                ", oeoreId='" + oeoreId + '\'' +
                ", prefDispStatus='" + prefDispStatus + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wardDesc);
        dest.writeString(this.bedCode);
        dest.writeString(this.patName);
        dest.writeString(this.drugName);
        dest.writeString(this.dispQty);
        dest.writeString(this.doseQty);
        dest.writeString(this.regNo);
        dest.writeString(this.inciId);
        dest.writeString(this.drugCode);
        dest.writeString(this.dispAuditId);
        dest.writeString(this.dispAuditStatus);
        dest.writeString(this.dispAuditStatusDesc);
        dest.writeString(this.oeoreId);
        dest.writeString(this.prefDispStatus);
    }

    public Drug() {
    }

    protected Drug(Parcel in) {
        this.wardDesc = in.readString();
        this.bedCode = in.readString();
        this.patName = in.readString();
        this.drugName = in.readString();
        this.dispQty = in.readString();
        this.doseQty = in.readString();
        this.regNo = in.readString();
        this.inciId = in.readString();
        this.drugCode = in.readString();
        this.dispAuditId = in.readString();
        this.dispAuditStatus = in.readString();
        this.dispAuditStatusDesc = in.readString();
        this.oeoreId = in.readString();
        this.prefDispStatus = in.readString();
    }

    public static final Creator<Drug> CREATOR = new Creator<Drug>() {
        @Override
        public Drug createFromParcel(Parcel source) {
            return new Drug(source);
        }

        @Override
        public Drug[] newArray(int size) {
            return new Drug[size];
        }
    };
}
