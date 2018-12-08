package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.fragemt.AllOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCheckFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillHandoverNurseFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillReceiveFragment;
import szszhospital.cn.com.mobilenurse.fragemt.DrugBillUnCompletedFragment;
import szszhospital.cn.com.mobilenurse.fragemt.EMRFragment;
import szszhospital.cn.com.mobilenurse.fragemt.HerbalOrderFragment;
import szszhospital.cn.com.mobilenurse.fragemt.InspectionFragment;
import szszhospital.cn.com.mobilenurse.fragemt.LisListFragment;
import szszhospital.cn.com.mobilenurse.fragemt.NOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.PacsListFragment;
import szszhospital.cn.com.mobilenurse.fragemt.SOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.TestFragment;
import szszhospital.cn.com.mobilenurse.fragemt.VitalSignsFragment;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;

/**
 * @author denghang
 */
public class FragmentFactory {

    public static BaseFragment newInstance(LocAccessResponse locAccess) {
        BaseFragment fragment = null;
        switch (locAccess.Model) {
            //未配药
            case "DrugBillUnCompletedFragment":
                fragment = DrugBillUnCompletedFragment.newInstance();
                break;
            // 已配药
            case "DrugBillCompletedFragment":
                fragment = DrugBillCompletedFragment.newInstance();
                break;
            // 接受
            case "DrugBillReceiveFragment":
                fragment = DrugBillReceiveFragment.newInstance();
                break;
            // 已交接
            case "DrugBillHandoverFragment":
                fragment = DrugBillHandoverFragment.newInstance();
                break;
            // 核对
            case "DrugBillCheckFragment":
                fragment = DrugBillCheckFragment.newInstance();
                break;
            // 待接收
            case "DrugBillHandoverNurseFragment":
                fragment = DrugBillHandoverNurseFragment.newInstance();
                break;
            // 电子病历
            case "EMRFragment":
                fragment = EMRFragment.newInstance();
                break;
            // Lis检验报告
            case "LisListFragment":
                fragment = LisListFragment.newInstance();
                break;
            // 临时医嘱
            case "NOrdersFragment":
                fragment = NOrdersFragment.newInstance();
                break;
            // PACS检查报告
            case "PacsListFragment":
                fragment = PacsListFragment.newInstance();
                break;
            // 长期医嘱
            case "SOrdersFragment":
                fragment = SOrdersFragment.newInstance();
                break;
            // 草药
            case "HerbalOrderFragment":
                fragment = HerbalOrderFragment.newInstance();
                break;
            //生命体征
            case "VitalSignsFragment":
                fragment = VitalSignsFragment.newInstance();
                break;
            //运送对：运送病人去检查
            case "InspectionFragment":
                fragment = InspectionFragment.newInstance();
                break;
            // 运送对：运送检验标本
            case "TestFragment":
                fragment = TestFragment.newInstance();
                break;
            //全部医嘱
            case "AllOrdersFragment":
                fragment = AllOrdersFragment.newInstance();
                break;
            default:
                break;
        }
        return fragment;
    }
}
