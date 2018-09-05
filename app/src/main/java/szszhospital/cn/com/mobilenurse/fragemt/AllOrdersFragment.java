package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 全部医嘱
 */
public class AllOrdersFragment extends BaseOrdersFragment {

    public static AllOrdersFragment newInstance() {
        return new AllOrdersFragment();
    }

    @Override
    protected String getOrderType() {
        return null;
    }
}
