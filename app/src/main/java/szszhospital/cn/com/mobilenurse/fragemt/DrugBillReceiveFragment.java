package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;

/**
 * 2018/5/20 01
 * 药品接受界面
 */
public class DrugBillReceiveFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_receiver;
    }

    public static DrugBillReceiveFragment newInstance() {
        return new DrugBillReceiveFragment();
    }
}
