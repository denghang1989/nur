package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCheckFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillSendFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnReceiveFragment;

public class FragmentFactory {

    public static BaseFragment newInstance(ModuleTable moduleTable) {
        BaseFragment fragment = null;
        switch (moduleTable.className) {
            case "DrugBillUnCompletedFragment":
                fragment = DrugBillUnCompletedFragment.newInstance();
                break;
            case "DrugBillCompletedFragment":
                fragment = DrugBillCompletedFragment.newInstance();
                break;
            case "DrugBillUnReceiveFragment":
                fragment = DrugBillUnReceiveFragment.newInstance();
                break;
            case "DrugBillSendFragment":
                fragment = DrugBillSendFragment.newInstance();
                break;
            case "DrugBillCheckFragment":
                fragment = DrugBillCheckFragment.newInstance();
                break;
        }
        return fragment;
    }
}
