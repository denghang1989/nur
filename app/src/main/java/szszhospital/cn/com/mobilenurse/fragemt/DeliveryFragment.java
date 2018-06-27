package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;

/**
 * 运送标本（这里的标本是指检验标本）
 */
public class DeliveryFragment extends BasePresenterFragment {

    @Override
    public int getLayoutId() {
        return 0;
    }

    public static DeliveryFragment newInstance() {
        return new DeliveryFragment();
    }
}
