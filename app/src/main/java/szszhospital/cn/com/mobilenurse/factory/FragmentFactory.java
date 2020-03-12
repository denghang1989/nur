package szszhospital.cn.com.mobilenurse.factory;

import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.fragemt.EMRFragment;
import szszhospital.cn.com.mobilenurse.fragemt.EposideVitalSignsFragment;
import szszhospital.cn.com.mobilenurse.fragemt.HerbalOrderFragment;
import szszhospital.cn.com.mobilenurse.fragemt.LisListFragment;
import szszhospital.cn.com.mobilenurse.fragemt.NOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.PacsOrderItemFragment;
import szszhospital.cn.com.mobilenurse.fragemt.PathologyOrderFragment;
import szszhospital.cn.com.mobilenurse.fragemt.SOrdersFragment;
import szszhospital.cn.com.mobilenurse.fragemt.VitalSignsFragment;
import szszhospital.cn.com.mobilenurse.mvp.presenter.EposideVitalSignsPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

/**
 * @author denghang
 */
public class FragmentFactory {

    public static BaseFragment newInstance(LocAccess locAccess) {
        BaseFragment fragment = null;
        switch (locAccess.Model) {
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
            case "PacsOrderItemFragment":
                fragment = PacsOrderItemFragment.newInstance();
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
            //病理检查：
            case "PathologyOrderFragment":
                fragment = PathologyOrderFragment.newInstance();
                break;
            case "EposideVitalSignsFragment":
                fragment = EposideVitalSignsFragment.newInstance();
            default:
                break;
        }
        return fragment;
    }
}
