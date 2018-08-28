package szszhospital.cn.com.mobilenurse.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtil {

    //字节大小，K,M,G
    public static final long KB = 1024;
    public static final long MB = KB * 1024;
    public static final long GB = MB * 1024;

    private AppUtil() {
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext().getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 验证网址Url
     *
     * @param
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    /**
     * @param regex
     * 正则表达式字符串
     * @param str
     * 要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 文件字节大小显示成M,G和K
     * @param size
     * @return
     */
    public static String displayFileSize(long size) {
        if (size >= GB) {
            return String.format("%.1f GB", (float) size / GB);
        } else if (size >= MB) {
            float value = (float) size / MB;
            return String.format(value > 100 ? "%.0f MB" : "%.1f MB", value);
        } else if (size >= KB) {
            float value = (float) size / KB;
            return String.format(value > 100 ? "%.0f KB" : "%.1f KB", value);
        } else {
            return String.format("%d B", size);
        }
    }

}
