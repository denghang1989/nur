package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class DispDetailResponse implements Parcelable {

    /**
     * PatNo : 01955447
     * PatName : 林维潘
     * Age : 45岁
     * Sex : 男
     * InciCode : 10800422
     * InciDesc : {市}碳酸钙D3片(600mg*30片)
     * Spec : 600mg*30片
     * StkBin : 11303
     * DispQty : 90
     * ConFirmQty :
     * ConFirmUser :
     * ConFirmDateTime :
     */

    public String PatNo;
    public String PatName;
    public String Age;
    public String Sex;
    public String InciCode;
    public String InciDesc;
    public String Spec;
    public String StkBin;
    public String DispQty;
    public String ConFirmQty;
    public String ConFirmUser;
    public String ConFirmDateTime;
    public String Bed;
    /**
     * AuditItmDr : 7||1
     * ConFirmFlag :
     */

    public String AuditItmDr;
    public String ConFirmFlag;
    public String ward;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.PatNo);
        dest.writeString(this.PatName);
        dest.writeString(this.Age);
        dest.writeString(this.Sex);
        dest.writeString(this.InciCode);
        dest.writeString(this.InciDesc);
        dest.writeString(this.Spec);
        dest.writeString(this.StkBin);
        dest.writeString(this.DispQty);
        dest.writeString(this.ConFirmQty);
        dest.writeString(this.ConFirmUser);
        dest.writeString(this.ConFirmDateTime);
        dest.writeString(this.Bed);
    }

    public DispDetailResponse() {
    }

    protected DispDetailResponse(Parcel in) {
        this.PatNo = in.readString();
        this.PatName = in.readString();
        this.Age = in.readString();
        this.Sex = in.readString();
        this.InciCode = in.readString();
        this.InciDesc = in.readString();
        this.Spec = in.readString();
        this.StkBin = in.readString();
        this.DispQty = in.readString();
        this.ConFirmQty = in.readString();
        this.ConFirmUser = in.readString();
        this.ConFirmDateTime = in.readString();
        this.Bed = in.readString();
    }

    public static final Creator<DispDetailResponse> CREATOR = new Creator<DispDetailResponse>() {
        @Override
        public DispDetailResponse createFromParcel(Parcel source) {
            return new DispDetailResponse(source);
        }

        @Override
        public DispDetailResponse[] newArray(int size) {
            return new DispDetailResponse[size];
        }
    };
}
