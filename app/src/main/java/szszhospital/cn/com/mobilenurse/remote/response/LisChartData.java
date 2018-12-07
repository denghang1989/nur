package szszhospital.cn.com.mobilenurse.remote.response;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LisChartData implements Comparable<LisChartData> {


    /**
     * AbFlag :
     * RefRanges : 3.50-5.30
     * ReportAuthDateTime : 20180907 12:36
     * Result : 3.61
     * TestCodeName : 钾[K+]
     * Units : mmol/L
     */

    public String AbFlag;
    public String RefRanges;
    public String ReportAuthDateTime;
    public String Result;
    public String TestCodeName;
    public String Units;

    @Override
    public int compareTo(@NonNull LisChartData o) {
        long o1 = TimeUtils.string2Millis(ReportAuthDateTime, new SimpleDateFormat("yyyyMMdd HH:mm", Locale.CHINA));
        long o2 = TimeUtils.string2Millis(o.ReportAuthDateTime, new SimpleDateFormat("yyyyMMdd HH:mm", Locale.CHINA));
        if (o1 - o2 > 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
