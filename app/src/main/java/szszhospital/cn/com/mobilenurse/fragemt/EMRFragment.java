package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 电子病历
 */
public class EMRFragment extends BaseDoctorFragment {

    public static EMRFragment newInstance() {
        return new EMRFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }
}
