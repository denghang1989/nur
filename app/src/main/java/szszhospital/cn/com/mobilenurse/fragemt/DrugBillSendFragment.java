package szszhospital.cn.com.mobilenurse.fragemt;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentSendDrugBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillContract;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 已发药
 */
public class DrugBillSendFragment extends BaseFragment<FragmentSendDrugBinding> implements DrugBillContract.View {

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
    public void showDrugBillList(List<DrugBill> list) {

    }
}
