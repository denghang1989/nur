package szszhospital.cn.com.mobilenurse.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;

public class Contants {

    public static List<ModuleTable> getModuleList(String locId, Context context) {
        List<ModuleTable> list = new ArrayList<>();
        switch (locId) {
            case "116":
                String[] drugArray = context.getResources().getStringArray(R.array.drug_list);
                for (int i = 0; i < drugArray.length; i++) {
                    ModuleTable moduleTable = new ModuleTable();
                    moduleTable.moduleDes=drugArray[i];
                    moduleTable.moduleId=i;
                    moduleTable.isActivity = true;
                    moduleTable.className="DispensingFragment";
                    list.add(moduleTable);
                }
                break;
            default:
                String[] nurseList = context.getResources().getStringArray(R.array.nurse_list);
                for (int i = 0; i < nurseList.length; i++) {
                    ModuleTable moduleTable = new ModuleTable();
                    moduleTable.moduleDes=nurseList[i];
                    moduleTable.moduleId=i;
                    moduleTable.isActivity = true;
                    moduleTable.className="DispensingFragment";
                    list.add(moduleTable);
                }
                break;
        }
        return list;
    }
}
