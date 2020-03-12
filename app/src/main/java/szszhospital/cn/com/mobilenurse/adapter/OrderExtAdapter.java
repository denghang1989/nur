package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.OrderExecuteInfo;

public class OrderExtAdapter extends BaseQuickAdapter<OrderExecuteInfo, BaseViewHolder> {

    public OrderExtAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderExecuteInfo item) {
        helper.setText(R.id.StartDateTime, item.TExStDate)
                .setText(R.id.status, item.TExecState)
                .setText(R.id.EndDateTime, item.TRealExecDate)
                .setText(R.id.user, item.TExecUser);
    }
}
