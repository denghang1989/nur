package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentVitalSignsBinding;

/**
 * 生命体征
 */
public class VitalSignsFragment extends BaseFragment<FragmentVitalSignsBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vital_signs;
    }

    public static VitalSignsFragment newInstance() {
        return new VitalSignsFragment();
    }
}
