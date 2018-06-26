package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PacsOrder implements Parcelable ,Serializable{

    /**
     * Grade : 评级
     * ImageUrl :
     * Memo : S^已发布
     * TImgBrowse : 图像
     * TImgShut :
     * TIsIll :
     * TIsModify :
     * TIsReaded : 未阅读
     * TIshasImg : N
     * TItemDate : 2018-06-22
     * TItemName : 肝胆脾彩色多普勒超声检查
     * TItemStatus :
     * TLocName : CSYX-超声影像科
     * TMediumName :
     * TOEOrderDr : 15465996||12
     * TOpenRpt : 报告
     * TRegNo : 3545499
     * TStudyNo : 1631170
     * TreplocDr : 14
     */

    public String Grade;
    public String ImageUrl;
    public String Memo;
    public String TImgBrowse;
    public String TImgShut;
    public String TIsIll;
    public String TIsModify;
    public String TIsReaded;
    public String TIshasImg;
    public String TItemDate;
    public String TItemName;
    public String TItemStatus;
    public String TLocName;
    public String TMediumName;
    public String TOEOrderDr;
    public String TOpenRpt;
    public String TRegNo;
    public String TStudyNo;
    public String TreplocDr;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Grade);
        dest.writeString(this.ImageUrl);
        dest.writeString(this.Memo);
        dest.writeString(this.TImgBrowse);
        dest.writeString(this.TImgShut);
        dest.writeString(this.TIsIll);
        dest.writeString(this.TIsModify);
        dest.writeString(this.TIsReaded);
        dest.writeString(this.TIshasImg);
        dest.writeString(this.TItemDate);
        dest.writeString(this.TItemName);
        dest.writeString(this.TItemStatus);
        dest.writeString(this.TLocName);
        dest.writeString(this.TMediumName);
        dest.writeString(this.TOEOrderDr);
        dest.writeString(this.TOpenRpt);
        dest.writeString(this.TRegNo);
        dest.writeString(this.TStudyNo);
        dest.writeString(this.TreplocDr);
    }

    public PacsOrder() {
    }

    protected PacsOrder(Parcel in) {
        this.Grade = in.readString();
        this.ImageUrl = in.readString();
        this.Memo = in.readString();
        this.TImgBrowse = in.readString();
        this.TImgShut = in.readString();
        this.TIsIll = in.readString();
        this.TIsModify = in.readString();
        this.TIsReaded = in.readString();
        this.TIshasImg = in.readString();
        this.TItemDate = in.readString();
        this.TItemName = in.readString();
        this.TItemStatus = in.readString();
        this.TLocName = in.readString();
        this.TMediumName = in.readString();
        this.TOEOrderDr = in.readString();
        this.TOpenRpt = in.readString();
        this.TRegNo = in.readString();
        this.TStudyNo = in.readString();
        this.TreplocDr = in.readString();
    }

    public static final Creator<PacsOrder> CREATOR = new Creator<PacsOrder>() {
        @Override
        public PacsOrder createFromParcel(Parcel source) {
            return new PacsOrder(source);
        }

        @Override
        public PacsOrder[] newArray(int size) {
            return new PacsOrder[size];
        }
    };
}
