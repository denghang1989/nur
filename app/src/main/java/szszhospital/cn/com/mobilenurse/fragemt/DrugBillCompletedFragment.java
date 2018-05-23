package szszhospital.cn.com.mobilenurse.fragemt;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.remote.request.SaveAuditStatusRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 00
 * 已配 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillCompletedFragment extends BaseDrugBillFragment {

    private SaveAuditStatusRequest mStatusRequest;

    public static DrugBillCompletedFragment newInstance() {
        return new DrugBillCompletedFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "A";
        mStatusRequest = new SaveAuditStatusRequest();
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DrugBill drugBill = mAdapter.getItem(position);
                mStatusRequest.Input = drugBill.AuditDr + "^" + "C" + "^" + App.loginUser.UserDR;
                mPresenter.saveAuditStatus(mStatusRequest);
            }
        });
    }
}
