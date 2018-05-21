package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentSendDrugBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillSendContract;

/**
 * 已发药
 */
public class DrugBillSendFragment extends BaseFragment<FragmentSendDrugBinding> implements DrugBillSendContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_send_drug;
    }

    public static DrugBillSendFragment newInstance() {
        return new DrugBillSendFragment();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDrugBillList() {

    }
}
