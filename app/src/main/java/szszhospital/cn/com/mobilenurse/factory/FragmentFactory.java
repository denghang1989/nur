package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.entity.ModuleTable;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCheckFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverNurseFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillReceiveFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.EMRFragment;
import szszhospital.cn.com.mobilenurse.fragemt.HerbalOrderFragment;
import szszhospital.cn.com.mobilenurse.fragemt.InspectionFragment;
import szszhospital.cn.com.mobilenurse.fragemt.LisResultFragment;
import szszhospital.cn.com.mobilenurse.fragemt.NOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.PacsResultFragment;
import szszhospital.cn.com.mobilenurse.fragemt.SOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.TestFragment;
import szszhospital.cn.com.mobilenurse.fragemt.VitalSignsFragment;

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
            case "EMRFragment": // 电子病历
                fragment = EMRFragment.newInstance();
                break;
            case "LisResultFragment": // Lis检验报告
                fragment = LisResultFragment.newInstance();
                break;
            case "NOrdersFragment": // 临时医嘱
                fragment = NOrdersFragment.newInstance();
                break;
            case "PacsResultFragment": // PACS检查报告
                fragment = PacsResultFragment.newInstance();
                break;
            case "SOrdersFragment": // 长期医嘱
                fragment = SOrdersFragment.newInstance();
                break;
            case "HerbalOrderFragment":// 草药
                fragment = HerbalOrderFragment.newInstance();
                break;
            case "VitalSignsFragment": //生命体征
                fragment = VitalSignsFragment.newInstance();
                break;
            case "InspectionFragment": //运送对：运送病人去检查
                fragment = InspectionFragment.newInstance();
                break;
            case "TestFragment": //
                fragment = TestFragment.newInstance();
                break;
        }
        return fragment;
    }
}
