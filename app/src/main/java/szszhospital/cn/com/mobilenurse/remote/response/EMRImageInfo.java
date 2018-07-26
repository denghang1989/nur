package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class EMRImageInfo implements Parcelable{

    /**
     * CategoryName : 门诊复诊病历
     * ImagePathURLs : ["/dhcepr/vol236/2353681/15681948/5030976/1.jpg"]
     * InternalID : HDSD00.03
     * PageCount : 1
     */

    public String       CategoryName;
    public String       InternalID;
    public String       PageCount;
    public List<String> ImagePathURLs;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CategoryName);
        dest.writeString(this.InternalID);
        dest.writeString(this.PageCount);
        dest.writeStringList(this.ImagePathURLs);
    }

    public EMRImageInfo() {
    }

    protected EMRImageInfo(Parcel in) {
        this.CategoryName = in.readString();
        this.InternalID = in.readString();
        this.PageCount = in.readString();
        this.ImagePathURLs = in.createStringArrayList();
    }

    public static final Creator<EMRImageInfo> CREATOR = new Creator<EMRImageInfo>() {
        @Override
        public EMRImageInfo createFromParcel(Parcel source) {
            return new EMRImageInfo(source);
        }

        @Override
        public EMRImageInfo[] newArray(int size) {
            return new EMRImageInfo[size];
        }
    };
}
