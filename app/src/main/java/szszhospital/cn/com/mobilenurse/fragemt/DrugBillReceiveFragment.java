package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 2018/5/20 01
 * 药品 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillReceiveFragment extends BaseDrugBillFragment {

    public static DrugBillReceiveFragment newInstance() {
        return new DrugBillReceiveFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "R";
    }

    @Override
    protected String getDialogText() {
        return "发药";
    }
}
