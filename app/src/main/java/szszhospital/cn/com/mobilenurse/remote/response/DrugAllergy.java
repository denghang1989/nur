package szszhospital.cn.com.mobilenurse.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class DrugAllergy implements Serializable , Parcelable{

    /**
     * AntibioticsClassDR : 1
     * AntibioticsDR : 130
     * AntibioticsName : 头孢他啶
     * IRanges :
     * RRanges : #<4
     * RowID : 75950
     * SName : CAZ
     * SRanges : #>16
     * SenMethod : 2
     * SenValue : 4
     * SensitivityDR : 2
     * SensitivityName : 敏感
     * Sequence : 0
     * VisitNumberReportResultDR : 24319627
     */

    public String AntibioticsClassDR;
    public String AntibioticsDR;
    public String AntibioticsName;
    public String IRanges;
    public String RRanges;
    public String RowID;
    public String SName;
    public String SRanges;
    public String SenMethod;
    public String SenValue;
    public String SensitivityDR;
    public String SensitivityName;
    public String Sequence;
    public String VisitNumberReportResultDR;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.AntibioticsClassDR);
        dest.writeString(this.AntibioticsDR);
        dest.writeString(this.AntibioticsName);
        dest.writeString(this.IRanges);
        dest.writeString(this.RRanges);
        dest.writeString(this.RowID);
        dest.writeString(this.SName);
        dest.writeString(this.SRanges);
        dest.writeString(this.SenMethod);
        dest.writeString(this.SenValue);
        dest.writeString(this.SensitivityDR);
        dest.writeString(this.SensitivityName);
        dest.writeString(this.Sequence);
        dest.writeString(this.VisitNumberReportResultDR);
    }

    public DrugAllergy() {
    }

    protected DrugAllergy(Parcel in) {
        this.AntibioticsClassDR = in.readString();
        this.AntibioticsDR = in.readString();
        this.AntibioticsName = in.readString();
        this.IRanges = in.readString();
        this.RRanges = in.readString();
        this.RowID = in.readString();
        this.SName = in.readString();
        this.SRanges = in.readString();
        this.SenMethod = in.readString();
        this.SenValue = in.readString();
        this.SensitivityDR = in.readString();
        this.SensitivityName = in.readString();
        this.Sequence = in.readString();
        this.VisitNumberReportResultDR = in.readString();
    }

    public static final Creator<DrugAllergy> CREATOR = new Creator<DrugAllergy>() {
        @Override
        public DrugAllergy createFromParcel(Parcel source) {
            return new DrugAllergy(source);
        }

        @Override
        public DrugAllergy[] newArray(int size) {
            return new DrugAllergy[size];
        }
    };
}
