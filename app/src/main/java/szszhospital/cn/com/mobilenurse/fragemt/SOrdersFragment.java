package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 长期医嘱
 */
public class SOrdersFragment extends BaseDoctorFragment {

    public static SOrdersFragment newInstance() {
        return new SOrdersFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

}
