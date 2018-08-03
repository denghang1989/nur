package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class CardPatientAdapter extends BaseQuickAdapter<PatientInfo,BaseViewHolder> {

    public CardPatientAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientInfo item) {

    }
}
