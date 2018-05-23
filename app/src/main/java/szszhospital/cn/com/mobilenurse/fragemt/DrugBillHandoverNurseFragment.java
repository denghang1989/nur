package szszhospital.cn.com.mobilenurse.fragemt;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.remote.request.SaveAuditStatusRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 交接 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillHandoverNurseFragment extends BaseDrugBillFragment {

    private SaveAuditStatusRequest mStatusRequest;
    private DrugBill mDrugBill;

    public static DrugBillHandoverNurseFragment newInstance() {
        return new DrugBillHandoverNurseFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "T";
        mStatusRequest = new SaveAuditStatusRequest();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            mDrugBill = mAdapter.getItem(position);
            showDialog(mDrugBill);
        });
    }

    @Override
    protected void updateAuditStatus(DrugBill drugBill) {
        mStatusRequest.Input = drugBill.AuditDr + "^" + "R" + "^" + App.loginUser.UserDR;
        mPresenter.saveAuditStatus(mStatusRequest);
    }

    @Override
    public void onPositive() {
        updateAuditStatus(mDrugBill);
    }
}
