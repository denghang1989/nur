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
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.PrescriptionActivity;
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
    protected DispDetailListDialogFragment mDialogFragment;
    protected DrugBill                     mDrugBill;

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

    private static final String TAG = "BaseDrugBillFragment";

    @Override
    public void showDrugBillList(List<DrugBill> list) {
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
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
        setOnItemClick();
    }

    protected void setOnItemClick() {
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            mDrugBill = mAdapter.getItem(position);
            showDialog(mDrugBill);
            //DrugCheckActivity.startDrugCheckActivity(_mActivity, mDrugBill.AuditDr, mDrugBill.DispNo);
        });
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
        if (code.startsWith("ZXYF")) {
            Optional<DrugBill> optional = Stream.of(mAdapter.getData()).filter(drugbill -> StringUtils.equalsIgnoreCase(code, drugbill.DispNo)).findFirst();
            if (!StringUtils.isTrimEmpty(mRequest.Flag)) {
                showDialog(optional.get());
                //DrugCheckActivity.startDrugCheckActivity(_mActivity, optional.get().AuditDr, optional.get().DispNo);
            } else {
                PrescriptionActivity.startPrescriptionActivity(_mActivity, optional.get().AuditDr, optional.get().DispNo);
            }
        } else if (code.startsWith("KF")) {
            // 处理包药机
            handlerKFCode(code);
        }

    }


    /**
     * @param code
     * @des 子类选择复写处理发药机
     */
    protected void handlerKFCode(String code) {

    }

    @Override
    public void onPositive() {
        updateAuditStatus(mDrugBill);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }

    @Override
    public void onNegative() {
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
    }

    protected void showDialog(DrugBill drugBill) {
        mDialogFragment = (DispDetailListDialogFragment) getChildFragmentManager().findFragmentByTag(KEY_TAG);
        if (mDialogFragment != null) {
            mDialogFragment.dismiss();
        }
        mDialogFragment = DispDetailListDialogFragment.newInstance(drugBill.AuditDr);
        mDialogFragment.setOkText(getDialogText());
        mDialogFragment.setDialogInterface(this);
        mDialogFragment.show(getChildFragmentManager(), "DispDetailListDialogFragment");
    }

    protected String getDialogText() {
        return "";
    }

    private static final String KEY_TAG = "DispDetailListDialogFragment";
}
