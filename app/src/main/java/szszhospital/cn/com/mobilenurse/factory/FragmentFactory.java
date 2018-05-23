package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.db.ModuleTable;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCheckFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverNurseFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillReceiveFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnCompletedFragment;

public class FragmentFactory {

    public static BaseFragment newInstance(ModuleTable moduleTable) {
        BaseFragment fragment = null;
        switch (moduleTable.className) {
            case "DrugBillUnCompletedFragment": //未配药
                fragment = DrugBillUnCompletedFragment.newInstance();
                break;
            case "DrugBillCompletedFragment": // 已配药
                fragment = DrugBillCompletedFragment.newInstance();
                break;
            case "DrugBillReceiveFragment": // 接受
                fragment = DrugBillReceiveFragment.newInstance();
                break;
            case "DrugBillHandoverFragment": // 已交接
                fragment = DrugBillHandoverFragment.newInstance();
                break;
            case "DrugBillCheckFragment": // 核对
                fragment = DrugBillCheckFragment.newInstance();
                break;
            case "DrugBillHandoverNurseFragment": // 待接收
                fragment = DrugBillHandoverNurseFragment.newInstance();
                break;
        }
        return fragment;
    }
}
