package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

public class LisResultAdapter extends BaseQuickAdapter<LisOrderDetail,BaseViewHolder> {

    public LisResultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LisOrderDetail item) {
        helper.setText(R.id.desc,item.TestCodeName)
                .setText(R.id.result,item.Result)
                .setText(R.id.hint,item.AbFlag)
                .setText(R.id.unit,item.Units)
                .setText(R.id.range,item.RefRanges);
    }
}
