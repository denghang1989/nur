package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * pacs 预约信息
 */
public class PacsOrderSubscribe implements Parcelable, Serializable {
    /**
     * AdmBed : 0006
     * Hosno : 223341
     * PatAge : 31岁
     * PatLoc : CSBQ-测试病区
     * PatName : 小赵
     * PatNo : 03435887
     * PatSex : 男
     * PrintFlag :
     * ReqData : 2018-06-27
     * ReqTime : 16:52
     * ReqUser : 张淑琼
     * ReserInfo :  --
     * Status :
     * StatusCode : 核实/执行
     * Type : 检查
     * arExLocDesc : FSYXK-放射影像科
     * arRepID : 294572
     * arReqNo : APPI2018062701233
     * arcListData : DR【鼻骨侧位*(1)、鼻咽侧位*(1)、蝶鞍侧位*(1)】
     * repEmgFlag : 否
     * transportStatus:A
     */

    public String AdmBed;
    public String Hosno;
    public String PatAge;
    public String PatLoc;
    public String PatName;
    public String PatNo;
    public String PatSex;
    public String PrintFlag;
    public String ReqData;
    public String ReqTime;
    public String ReqUser;
    public String ReserInfo;
    public String Status;
    public String StatusCode;
    public String Type;
    public String arExLocDesc;
    public String arRepID;
    public String arReqNo;
    public String arcListData;
    public String repEmgFlag;
    public String transportStatus;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AdmBed);
        dest.writeString(this.Hosno);
        dest.writeString(this.PatAge);
        dest.writeString(this.PatLoc);
        dest.writeString(this.PatName);
        dest.writeString(this.PatNo);
        dest.writeString(this.PatSex);
        dest.writeString(this.PrintFlag);
        dest.writeString(this.ReqData);
        dest.writeString(this.ReqTime);
        dest.writeString(this.ReqUser);
        dest.writeString(this.ReserInfo);
        dest.writeString(this.Status);
        dest.writeString(this.StatusCode);
        dest.writeString(this.Type);
        dest.writeString(this.arExLocDesc);
        dest.writeString(this.arRepID);
        dest.writeString(this.arReqNo);
        dest.writeString(this.arcListData);
        dest.writeString(this.repEmgFlag);
        dest.writeString(this.transportStatus);
    }

    public PacsOrderSubscribe() {
    }

    protected PacsOrderSubscribe(Parcel in) {
        this.AdmBed = in.readString();
        this.Hosno = in.readString();
        this.PatAge = in.readString();
        this.PatLoc = in.readString();
        this.PatName = in.readString();
        this.PatNo = in.readString();
        this.PatSex = in.readString();
        this.PrintFlag = in.readString();
        this.ReqData = in.readString();
        this.ReqTime = in.readString();
        this.ReqUser = in.readString();
        this.ReserInfo = in.readString();
        this.Status = in.readString();
        this.StatusCode = in.readString();
        this.Type = in.readString();
        this.arExLocDesc = in.readString();
        this.arRepID = in.readString();
        this.arReqNo = in.readString();
        this.arcListData = in.readString();
        this.repEmgFlag = in.readString();
        this.transportStatus = in.readString();
    }

    public static final Creator<PacsOrderSubscribe> CREATOR = new Creator<PacsOrderSubscribe>() {
        @Override
        public PacsOrderSubscribe createFromParcel(Parcel source) {
            return new PacsOrderSubscribe(source);
        }

        @Override
        public PacsOrderSubscribe[] newArray(int size) {
            return new PacsOrderSubscribe[size];
        }
    };
}
