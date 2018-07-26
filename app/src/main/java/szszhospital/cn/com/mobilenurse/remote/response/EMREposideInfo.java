package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class EMREposideInfo implements Serializable,Parcelable{

    /**
     * CategoryName : 门诊复诊病历
     * DocID : 106
     * InternalID : HDSD00.03
     */

    public String CategoryName;
    public String DocID;
    public String InternalID;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CategoryName);
        dest.writeString(this.DocID);
        dest.writeString(this.InternalID);
    }

    public EMREposideInfo() {
    }

    protected EMREposideInfo(Parcel in) {
        this.CategoryName = in.readString();
        this.DocID = in.readString();
        this.InternalID = in.readString();
    }

    public static final Creator<EMREposideInfo> CREATOR = new Creator<EMREposideInfo>() {
        @Override
        public EMREposideInfo createFromParcel(Parcel source) {
            return new EMREposideInfo(source);
        }

        @Override
        public EMREposideInfo[] newArray(int size) {
            return new EMREposideInfo[size];
        }
    };
}
