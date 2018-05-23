package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugBillListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentUnDrugBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 00
 * 发药流程：发药单状态变化的基类；
 */
public class BaseDrugBillFragment extends BasePresenterFragment<FragmentUnDrugBinding, DrugBillPresenter> implements DrugBillContract.View {
    protected DrugBillListAdapter mAdapter;
    protected DrugBillListRequest mRequest;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_un_drug;
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new DrugBillListAdapter(R.layout.item_drug_bill_list);
        mRequest = new DrugBillListRequest();
        mRequest.User = App.loginUser.UserDR;
        mRequest.Loc = App.loginUser.UserLoc;
        mRequest.Flag = "";
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void hideProgress() {
        mDataBinding.refreshLayout.finishRefresh();
    }

    @Override
    public void showDrugBillList(List<DrugBill> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        mDataBinding.refreshLayout.autoRefresh();
    }

    @Override
    protected DrugBillPresenter initPresenter() {
        return new DrugBillPresenter();
    }

    @Override
    protected void initView() {
        mDataBinding.unDrugListview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.unDrugListview.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.unDrugListview.setAdapter(mAdapter);
        mDataBinding.refreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void initData() {

    }

    protected void getDrugListData() {
        mPresenter.getDrugBillList(mRequest);
    }

    @Override
    protected void initEvent() {
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> getDrugListData());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mDataBinding.refreshLayout.autoRefresh();
    }
}
