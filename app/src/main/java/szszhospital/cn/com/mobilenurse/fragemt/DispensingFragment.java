package szszhospital.cn.com.mobilenurse.fragemt;

import android.util.Log;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseScanFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDispensingBinding;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispensingPresenter;

public class DispensingFragment extends BaseScanFragment<FragmentDispensingBinding, DispensingPresenter> {
    private static final String TAG = "DispensingFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dispensing;
    }

    public static DispensingFragment newInstance() {
        return new DispensingFragment();
    }

    @Override
    protected void handlerCode(String code) {
        super.handlerCode(code);
        Log.d(TAG, "handlerCode: " + code);
    }
}
