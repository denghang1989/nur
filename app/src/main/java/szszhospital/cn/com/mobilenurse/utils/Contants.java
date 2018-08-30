package szszhospital.cn.com.mobilenurse.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.entity.ModuleTable;

public class Contants {

    public static final String PHOTO_PATH             = "http://172.18.0.27/dhcemr";   //电子病历图片数据
    public static final String PACS_PATH              = "http://172.18.0.143/annet"; //pacs图像地址
    public static final String PACS_DCM_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pacs";
    public static final String PDF_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf";

    public static List<ModuleTable> getModuleList(String locId, Context context) {
        List<ModuleTable> list = new ArrayList<>();
        switch (locId) {
            case "116": //中心药房
                String[] drugArray = context.getResources().getStringArray(R.array.drug_list);
                for (int i = 0; i < drugArray.length; i++) {
                    ModuleTable moduleTable = new ModuleTable();
                    String[] split = drugArray[i].split("\\|");
                    moduleTable.moduleDes = split[0];
                    moduleTable.moduleId = i;
                    moduleTable.isActivity = true;
                    moduleTable.className = split[1];
                    list.add(moduleTable);
                }
                break;
            default:
                String[] nurseList = context.getResources().getStringArray(R.array.nurse_list);
                for (int i = 0; i < nurseList.length; i++) {
                    ModuleTable moduleTable = new ModuleTable();
                    String[] split = nurseList[i].split("\\|");
                    moduleTable.moduleDes = split[0];
                    moduleTable.moduleId = i;
                    moduleTable.isActivity = true;
                    moduleTable.className = split[1];
                    list.add(moduleTable);
                }
                break;
        }
        return list;
    }
}
