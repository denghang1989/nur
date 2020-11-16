package szszhospital.cn.com.mobilenurse.remote.response;

import com.blankj.utilcode.util.TimeUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;

public class PatientInfo implements  Serializable, Comparable<PatientInfo> {

    /**
     * Age : 27岁
     * Diagnose : 2型糖尿病
     * DisBed : 0002
     * Doctor : doctor
     * EpisodeID : 12528188
     * MedicareNo : 203946
     * PAPMIName : 测试一
     * PaAdmDateTime : 2017-01-19 09:14
     * PatientID : 2920389
     * PayType : 医保
     * Sex : 男
     */

    public String Age;
    public String Diagnose;
    public String DisBed;
    public String Doctor;
    public String EpisodeID;
    public String MedicareNo;
    public String PAPMIName;
    public String PaAdmDateTime;
    public String PatientID;
    public String PayType;
    public String Sex;
    /**
     * PatientNo : 00879448
     */

    public String PatientNo;
    /**
     * MedicareNo : 4888
     * PatientID : 1
     * PatientNo : 1
     * Type : H
     */

    public String Type;
    /**
     * Loc :
     * MedicareNo : 10007
     * PatientID : 4
     * PatientNo : 4
     */

    public String Loc;
    /**
     * Days : 4
     * MedicareNo : 170501
     * PatientID : 113253
     * PatientNo : 138952
     */

    public String Days;


    @Override
    public int compareTo(@NonNull PatientInfo o) {
        long o1Time = TimeUtils.string2Millis(PaAdmDateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        long o2Time = TimeUtils.string2Millis(o.PaAdmDateTime, new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        if (o1Time > o2Time) {
            return -1;
        } else {
            return 1;
        }
    }
}
