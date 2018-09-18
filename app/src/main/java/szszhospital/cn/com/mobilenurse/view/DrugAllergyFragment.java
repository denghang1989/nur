package szszhospital.cn.com.mobilenurse.view;

import android.os.Bundle;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseDialogFragment;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

/**
 * 药敏结果
 */
public class DrugAllergyFragment extends BaseDialogFragment {

    private static final String DATA = "data";
    private LisOrderDetail mLisOrderDetail;

    public static DrugAllergyFragment newInstance(LisOrderDetail lisdetail) {
        Bundle args = new Bundle();
        args.putParcelable(DATA, lisdetail);
        DrugAllergyFragment fragment = new DrugAllergyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void init() {
        mLisOrderDetail = getArguments().getParcelable(DATA);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_allergy;
    }

    @Override
    protected void initData() {

    }
}
