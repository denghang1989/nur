package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 临时医嘱
 */
public class NOrdersFragment extends BaseOrdersFragment{

    public static NOrdersFragment newInstance() {
        return new NOrdersFragment();
    }

    @Override
    protected String getOrderType() {
        return "N";
    }
}
