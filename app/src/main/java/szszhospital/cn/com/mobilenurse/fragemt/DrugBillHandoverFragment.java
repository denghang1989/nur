package szszhospital.cn.com.mobilenurse.fragemt;

/**
 * 交接 ""-未配药;A-已配;C-核对;T-交接;R-接收 （中心药房）
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

    @Override
    public void onPositive() {
        mDialogFragment.dismiss();
    }
}
