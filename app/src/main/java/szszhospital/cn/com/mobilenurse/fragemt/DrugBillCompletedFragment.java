package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.QRCodeActivity;
import szszhospital.cn.com.mobilenurse.adapter.DrugBillListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDrugCompletedBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillUnCompletedContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillCompletedPresenterImpl;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 00
 * 获取当前配药完成的发药单
 */
public class DrugBillCompletedFragment extends BasePresenterFragment<FragmentDrugCompletedBinding, DrugBillCompletedPresenterImpl> implements DrugBillUnCompletedContract.View {

    private DrugBillListAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_completed;
    }

    public static DrugBillCompletedFragment newInstance() {
        return new DrugBillCompletedFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new DrugBillListAdapter(R.layout.item_drug_bill_list);
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.listviewCompleted.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.listviewCompleted.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.HORIZONTAL));
        mDataBinding.listviewCompleted.setAdapter(mAdapter);
        mDataBinding.refreshLayoutCompleted.setEnableLoadmore(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDrugBillList(List<DrugBill> list) {

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.refreshLayoutCompleted.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QRCodeActivity.startQRCodeActivity(_mActivity, position + "");
            }
        });
    }
}
