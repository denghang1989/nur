package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDrugCompletedBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillUnCompletedContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillCompletedPresenterImpl;

/**
 * 2018/5/20 00
 * 获取当前配药完成的发药单
 */
public class DrugBillCompletedFragment extends BasePresenterFragment<FragmentDrugCompletedBinding, DrugBillCompletedPresenterImpl> implements DrugBillUnCompletedContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_completed;
    }

    public static DrugBillCompletedFragment newInstance() {
        return new DrugBillCompletedFragment();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDrugBillList() {

    }
}
