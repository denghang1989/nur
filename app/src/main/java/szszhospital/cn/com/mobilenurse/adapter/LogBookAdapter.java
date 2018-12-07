package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LogBook;

public class LogBookAdapter extends BaseQuickAdapter<LogBook, BaseViewHolder> {

    public LogBookAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogBook item) {
        String patientInfo = mContext.getResources().getString(R.string.patientInfo, item.bed, item.name, item.sex, item.age);
        helper.setText(R.id.diagnose, item.diagnose)
                .setText(R.id.type, item.type)
                .setText(R.id.info, patientInfo)
                .setText(R.id.condition, item.condition)
                .setText(R.id.operation, item.operation)
                .setText(R.id.handle, item.Item112);
    }
}
