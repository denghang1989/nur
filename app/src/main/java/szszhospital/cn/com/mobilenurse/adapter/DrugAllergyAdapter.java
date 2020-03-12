package szszhospital.cn.com.mobilenurse.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DrugAllergy;

public class DrugAllergyAdapter extends BaseQuickAdapter<DrugAllergy,BaseViewHolder> {

    public DrugAllergyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugAllergy item) {
        helper.setText(R.id.name,item.AntibioticsName)
                .setText(R.id.kb,"")
                .setText(R.id.mic,item.SenValue)
                .setText(R.id.result,item.SensitivityName);
    }
}
