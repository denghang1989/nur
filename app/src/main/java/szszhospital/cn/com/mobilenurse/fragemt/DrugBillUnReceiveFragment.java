package szszhospital.cn.com.mobilenurse.fragemt;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDrugReceiverBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 01
 * 药品接受界面(需要扫描二维码)
 */
public class DrugBillUnReceiveFragment extends BasePresenterFragment<FragmentDrugReceiverBinding, DrugBillPresenter> implements DrugBillContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_receiver;
    }

    public static DrugBillUnReceiveFragment newInstance() {
        return new DrugBillUnReceiveFragment();
    }

    @Override
    protected DrugBillPresenter initPresenter() {
        return new DrugBillPresenter();
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
