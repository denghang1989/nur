package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 长期医嘱
 */
public class SOrdersFragment extends BaseOrdersFragment{

    public static SOrdersFragment newInstance() {
        return new SOrdersFragment();
    }

    @Override
    protected String getOrderType() {
        return "S";
    }
}
