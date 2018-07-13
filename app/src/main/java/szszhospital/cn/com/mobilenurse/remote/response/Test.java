package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Test implements Parcelable,Serializable{


    /**
     * AdmType : 门诊
     * Age : 34
     * BedNo :
     * Diagnosem : 阴道和外阴特指炎症,带下病
     * Doctor : 周紫琼
     * EposideId : 15594984
     * LabNo : 7938589
     * LocDesc : 妇科门诊
     * PatientName : 李慧柳
     * PatientNo : 141357
     * TestSetDesc : 支原体培养及药敏
     * Ward :
     * Status：A
     */

    public String AdmType;
    public String Age;
    public String BedNo;
    public String Diagnosem;
    public String Doctor;
    public String EposideId;
    public String LabNo;
    public String LocDesc;
    public String PatientName;
    public String PatientNo;
    public String TestSetDesc;
    public String Ward;
    public String Status;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AdmType);
        dest.writeString(this.Age);
        dest.writeString(this.BedNo);
        dest.writeString(this.Diagnosem);
        dest.writeString(this.Doctor);
        dest.writeString(this.EposideId);
        dest.writeString(this.LabNo);
        dest.writeString(this.LocDesc);
        dest.writeString(this.PatientName);
        dest.writeString(this.PatientNo);
        dest.writeString(this.TestSetDesc);
        dest.writeString(this.Ward);
        dest.writeString(this.Status);
    }

    public Test() {
    }

    protected Test(Parcel in) {
        this.AdmType = in.readString();
        this.Age = in.readString();
        this.BedNo = in.readString();
        this.Diagnosem = in.readString();
        this.Doctor = in.readString();
        this.EposideId = in.readString();
        this.LabNo = in.readString();
        this.LocDesc = in.readString();
        this.PatientName = in.readString();
        this.PatientNo = in.readString();
        this.TestSetDesc = in.readString();
        this.Ward = in.readString();
        this.Status = in.readString();
    }

    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel source) {
            return new Test(source);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };
}
