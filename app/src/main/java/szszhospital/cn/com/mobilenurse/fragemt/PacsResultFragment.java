package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;

/**
 * PACS检查报告
 */
public class PacsResultFragment extends BaseDoctorFragment {

    public static PacsResultFragment newInstance() {
        return new PacsResultFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }
}
