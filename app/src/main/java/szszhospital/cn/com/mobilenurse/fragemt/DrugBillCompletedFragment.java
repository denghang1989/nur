package szszhospital.cn.com.mobilenurse.fragemt;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import szszhospital.cn.com.mobilenurse.activity.QRCodeActivity;

/**
 * 2018/5/20 00
 * 获取当前配药完成的发药单 ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugBillCompletedFragment extends BaseDrugBillFragment {

    public static DrugBillCompletedFragment newInstance() {
        return new DrugBillCompletedFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "A";
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QRCodeActivity.startQRCodeActivity(_mActivity, position + "");
            }
        });
    }
}
