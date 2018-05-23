package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.remote.request.SaveAuditStatusRequest;

/**
 * 交接 ""-未配药;A-已配;C-核对;T-交接;R-接收 （中心药房）
 */
public class DrugBillHandoverFragment extends BaseDrugBillFragment {

    private SaveAuditStatusRequest mStatusRequest;

    public static DrugBillHandoverFragment newInstance() {
        return new DrugBillHandoverFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "T";
        mStatusRequest = new SaveAuditStatusRequest();
    }
}
