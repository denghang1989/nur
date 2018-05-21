package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentSendDrugBinding;

public class DrugBillSendFragment extends BaseFragment<FragmentSendDrugBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_send_drug;
    }

    public static DrugBillSendFragment newInstance() {
        return new DrugBillSendFragment();
    }
}
