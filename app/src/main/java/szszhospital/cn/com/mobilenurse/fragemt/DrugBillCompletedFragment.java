package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;

/**
 * 2018/5/20 00
 * 获取当前配药完成的发药单
 */
public class DrugBillCompletedFragment extends BasePresenterFragment{
    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_completed;
    }

    public static DrugBillCompletedFragment newInstance() {
        return new DrugBillCompletedFragment();
    }
}
