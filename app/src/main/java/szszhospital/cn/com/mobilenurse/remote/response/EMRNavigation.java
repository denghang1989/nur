package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class EMRNavigation implements Serializable,Parcelable{


    /**
     * EMRTemplateCategoryID : 1
     * ItemCode : 16
     * ItemSeq : 155
     * ItemTitle : 病案首页
     */

    public String EMRTemplateCategoryID;
    public String ItemCode;
    public int ItemSeq;
    public String ItemTitle;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.EMRTemplateCategoryID);
        dest.writeString(this.ItemCode);
        dest.writeInt(this.ItemSeq);
        dest.writeString(this.ItemTitle);
    }

    public EMRNavigation() {
    }

    protected EMRNavigation(Parcel in) {
        this.EMRTemplateCategoryID = in.readString();
        this.ItemCode = in.readString();
        this.ItemSeq = in.readInt();
        this.ItemTitle = in.readString();
    }

    public static final Creator<EMRNavigation> CREATOR = new Creator<EMRNavigation>() {
        @Override
        public EMRNavigation createFromParcel(Parcel source) {
            return new EMRNavigation(source);
        }

        @Override
        public EMRNavigation[] newArray(int size) {
            return new EMRNavigation[size];
        }
    };
}
