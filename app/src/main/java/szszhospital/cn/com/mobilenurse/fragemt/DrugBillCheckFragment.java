package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 已核对发药单 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillCheckFragment extends BaseDrugBillFragment {

    public static DrugBillCheckFragment newInstance() {
        return new DrugBillCheckFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag="C";
    }
}
