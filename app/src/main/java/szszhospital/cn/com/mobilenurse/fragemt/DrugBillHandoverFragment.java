package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 已发药 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillHandoverFragment extends BaseDrugBillFragment {

    public static DrugBillHandoverFragment newInstance() {
        return new DrugBillHandoverFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "T";
    }
}
