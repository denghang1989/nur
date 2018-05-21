package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseScanFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDrugReceiverBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillReceiveContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillReceivePresenterImpl;

/**
 * 2018/5/20 01
 * 药品接受界面(需要扫描二维码)
 */
public class DrugBillUnReceiveFragment extends BaseScanFragment<FragmentDrugReceiverBinding, DrugBillReceivePresenterImpl> implements DrugBillReceiveContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_receiver;
    }

    public static DrugBillUnReceiveFragment newInstance() {
        return new DrugBillUnReceiveFragment();
    }
}