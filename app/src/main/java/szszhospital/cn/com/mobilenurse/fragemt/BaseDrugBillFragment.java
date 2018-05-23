package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.DialogInterface;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugBillListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentUnDrugBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.DrugBillContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DrugBillPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.DrugBillListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;

/**
 * 2018/5/20 00
 * 发药流程：发药单状态变化的基类；
 */
public class BaseDrugBillFragment extends BasePresenterFragment<FragmentUnDrugBinding, DrugBillPresenter> implements DrugBillContract.View, DialogInterface {
    protected DrugBillListAdapter          mAdapter;
    protected DrugBillListRequest          mRequest;
    protected DispDetailListDialogFragment mFragment;


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
        mPresenter.getDrugBillList(mRequest);
    }

    @Override
    protected void initEvent() {
        mDataBinding.refreshLayout.setOnRefreshListener(refreshlayout -> initData());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        initData();
    }

    protected void updateAuditStatus(DrugBill drugBill) {

    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCode(QRCodeEvent event) {
        String code = event.code;
        Optional<DrugBill> optional = Stream.of(mAdapter.getData()).filter(drugbill -> StringUtils.equalsIgnoreCase(code, drugbill.DispNo)).findFirst();
        updateAuditStatus(optional.get());
    }

    @Override
    public void onPositive() {

    }

    @Override
    public void onNegative() {
        if (mFragment != null) {
            mFragment.dismiss();
        }
    }

    protected void showDialog(DrugBill drugBill) {
        mFragment = (DispDetailListDialogFragment) getChildFragmentManager().findFragmentByTag(KEY_TAG);
        if (mFragment != null) {
            mFragment.dismiss();
        }
        mFragment = DispDetailListDialogFragment.newInstance(drugBill.AuditDr);
        mFragment.setDialogInterface(this);
        mFragment.show(getChildFragmentManager(), "DispDetailListDialogFragment");
    }

    private static final String KEY_TAG = "DispDetailListDialogFragment";
}
