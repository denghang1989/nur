package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseScanFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDispensingBinding;

public class DispensingFragment extends BaseScanFragment<FragmentDispensingBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_dispensing;
    }

    public static DispensingFragment newInstance() {
        return new DispensingFragment();
    }
}
