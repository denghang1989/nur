package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisOrderDetailAdapter extends BaseQuickAdapter<LisOrderDetail, BaseViewHolder> {

    public LisOrderDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LisOrderDetail item) {
        helper.setText(R.id.name,item.TestCodeName)
                .setText(R.id.value,item.Result)
                .setText(R.id.remind,item.AbFlag)
                .setText(R.id.company,item.Units)
                .setText(R.id.range,item.RefRanges);

        if (!StringUtils.isTrimEmpty(item.AbFlag)) {
            helper.setBackgroundRes(R.id.remind,R.drawable.remind_l);
        } else {
            helper.setBackgroundRes(R.id.remind,R.drawable.remind_n);
        }
    }
}
