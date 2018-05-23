package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.remote.request.SaveAuditStatusRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 已核 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillCheckFragment extends BaseDrugBillFragment {

    private SaveAuditStatusRequest mStatusRequest;

    public static DrugBillCheckFragment newInstance() {
        return new DrugBillCheckFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "C";
        mStatusRequest = new SaveAuditStatusRequest();
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            DrugBill drugBill = mAdapter.getItem(position);
            updateAuditStatus(drugBill);
        });
    }

    @Override
    protected void updateAuditStatus(DrugBill drugBill) {
        mStatusRequest.Input = drugBill.AuditDr + "^" + "T" + "^" + App.loginUser.UserDR;
        mPresenter.saveAuditStatus(mStatusRequest);
    }
}
