package szszhospital.cn.com.mobilenurse.fragemt;

import android.content.Intent;

import szszhospital.cn.com.mobilenurse.activity.PrescriptionActivity;

/**
 * 2018/5/20 00
 * 获取当前未配药完成的发药单列表(当前界面不需要扫描二维码)
 */
public class DrugBillUnCompletedFragment extends BaseDrugBillFragment {

    public static final int REQUEST_CODE = 200;

    public static DrugBillUnCompletedFragment newInstance() {
        return new DrugBillUnCompletedFragment();
    }

    @Override
    protected void init() {
        super.init();
        mRequest.Flag = "";
    }

    @Override
    protected void setOnItemClick() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //判断一下当前配药单的状态>未发药的状态
            PrescriptionActivity.startPrescriptionActivity(_mActivity, mAdapter.getItem(position));
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            initData();
        }
    }

    /**
     * @param code 发药机的code：KF000000000
     */
    @Override
    protected void handlerKFCode(String code) {

    }
}
