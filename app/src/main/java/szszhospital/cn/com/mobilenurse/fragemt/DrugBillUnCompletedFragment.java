package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;

/**
 * 2018/5/20 00
 * 获取当前未配药完成的发药单列表(当前界面不需要扫描二维码)
 */
public class DrugBillUnCompletedFragment extends BasePresenterFragment {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_un_drug;
    }

    public static DrugBillUnCompletedFragment newInstance() {
        return new DrugBillUnCompletedFragment();
    }
}
