package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseScanFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDispensingBinding;

public class DispensingFragment extends BaseScanFragment<FragmentDispensingBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_dispensing;
    }

    public static DispensingFragment newInstance() {
        Bundle args = new Bundle();
        DispensingFragment fragment = new DispensingFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
