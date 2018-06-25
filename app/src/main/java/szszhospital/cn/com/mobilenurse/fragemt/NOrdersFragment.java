package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;

/**
 * 临时医嘱
 */
public class NOrdersFragment extends BaseDoctorFragment {

    public static NOrdersFragment newInstance() {
        return new NOrdersFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }
}
