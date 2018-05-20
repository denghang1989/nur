package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;
import szszhospital.cn.com.mobilenurse.fragemt.DispensingFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillReceiveFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnCompletedFragment;

public class FragmentFactory {

    public static BaseFragment newInstance(ModuleTable moduleTable) {
        BaseFragment fragment = null;
        switch (moduleTable.className) {
            case "DispensingFragment":
                fragment = DispensingFragment.newInstance();
                break;
            case "DrugBillUnCompletedFragment":
                fragment = DrugBillUnCompletedFragment.newInstance();
                break;
            case "DrugBillCompletedFragment":
                fragment = DrugBillCompletedFragment.newInstance();
                break;
            case "DrugBillReceiveFragment":
                fragment = DrugBillReceiveFragment.newInstance();
                break;
            default:
                fragment = DispensingFragment.newInstance();
                break;
        }
        return fragment;
    }
}
