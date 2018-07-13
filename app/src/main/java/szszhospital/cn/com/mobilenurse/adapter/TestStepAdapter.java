package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.TestStep;

public class TestStepAdapter extends BaseQuickAdapter<TestStep,BaseViewHolder>{
    public TestStepAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestStep item) {
        helper.setText(R.id.name, "操作人：" + item.OperateUserName)
                .setText(R.id.dateTime, "操作时间：" + item.OperateDateTime)
                .setText(R.id.status, "状态：" + item.Action);
        int indexOf = getData().indexOf(item) + 1;
        if (indexOf < getData().size()) {
            helper.setImageResource(R.id.icon, R.drawable.icon_complete);
        } else {
            helper.setImageResource(R.id.icon, R.drawable.icon_underway);
        }
    }
}
