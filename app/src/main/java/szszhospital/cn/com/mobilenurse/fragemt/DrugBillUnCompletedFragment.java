package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.DispensingActivity;
import szszhospital.cn.com.mobilenurse.adapter.DrugBillListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentUnDrugBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillUnCompletedContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillUnCompletedPresenter;

/**
 * 2018/5/20 00
 * 获取当前未配药完成的发药单列表(当前界面不需要扫描二维码)
 */
public class DrugBillUnCompletedFragment extends BasePresenterFragment<FragmentUnDrugBinding, DrugBillUnCompletedPresenter> implements DrugBillUnCompletedContract.View {
    private static final String TAG = "DrugBillUnCompletedFrag";
    private DrugBillListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_un_drug;
    }

    public static DrugBillUnCompletedFragment newInstance() {
        return new DrugBillUnCompletedFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new DrugBillListAdapter(R.layout.item_drug_bill_list);
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void showDrugBillList() {

    }

    @Override
    protected DrugBillUnCompletedPresenter initPresenter() {
        return new DrugBillUnCompletedPresenter();
    }

    @Override
    protected void initView() {
        mDataBinding.unDrugListview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.unDrugListview.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.HORIZONTAL));
        mDataBinding.unDrugListview.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initData() {
        mPresenter.getDrugBillList();
    }

    @Override
    protected void initEvent() {
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //判断一下当前配药单的状态>未发药的状态
                DispensingActivity.startDispensingActivity(_mActivity, position + "");
            }
        });
    }

}
