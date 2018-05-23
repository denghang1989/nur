package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugListAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentDispensingBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispDetailPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.AuditDetailRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

/**
 * 配药界面明细的Fragment
 */
public class PrescriptionFragment extends BasePresenterFragment<FragmentDispensingBinding, DispDetailPresenter> implements DispDetailContract.View {
    private static final String TAG            = "PrescriptionFragment";
    private static final String KEY_DATA       = "data";
    private static final String KEY_PATIENT_NO = "key_patient_no";
    private DrugListAdapter mAdapter;

    /**
     * 病人本次就诊号
     */
    private String             mPatientNo;
    private View               mHeadView;
    private AuditDetailRequest mRequest;

    public static PrescriptionFragment newInstance(ArrayList<DispDetailResponse> drugList, String patientNo) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA, drugList);
        args.putString(KEY_PATIENT_NO, patientNo);
        PrescriptionFragment fragment = new PrescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dispensing;
    }

    @Override
    protected void init() {
        super.init();
        setSwipeBackEnable(false);
        mPatientNo = getArguments().getString(KEY_PATIENT_NO);
        mRequest = new AuditDetailRequest();
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.drugList.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.drugList.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mAdapter = new DrugListAdapter(R.layout.item_drug);
        mDataBinding.drugList.setAdapter(mAdapter);
        mHeadView = LayoutInflater.from(_mActivity).inflate(R.layout.item_drug_head, null);
        mAdapter.addHeaderView(mHeadView);
    }

    @Override
    public void showProgress() {
        mDataBinding.drugProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.drugProgress.setVisibility(View.GONE);
    }

    @Override
    public void refresh() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void dispComplete() {
        _mActivity.finish();
    }

    @Override
    protected DispDetailPresenter initPresenter() {
        return new DispDetailPresenter();
    }

    public void setDrugBillList(List<DispDetailResponse> list) {
        mAdapter.setNewData(list);
    }

    @Override
    protected void initData() {
        super.initData();
        ArrayList<DispDetailResponse> list = getArguments().getParcelableArrayList(KEY_DATA);
        if (list != null && mPatientNo != null) {
            List<DispDetailResponse> responseList = Stream.of(list).filter(response -> StringUtils.equals(mPatientNo, response.PatNo)).collect(Collectors.toList());
            setHeadViewData(responseList.get(0));
            setDrugBillList(responseList);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DispDetailResponse item = mAdapter.getItem(position);
                if (StringUtils.isTrimEmpty(item.ConFirmFlag)) {
                    mRequest.Input = item.AuditItmDr + "^" + item.DispQty + "^" + App.loginUser.UserDR;
                    mPresenter.updateDrugState(mRequest, item);
                } else {
                    ToastUtils.showShort("已配药！");
                }
            }
        });
    }

    private void setHeadViewData(DispDetailResponse response) {
        if (mHeadView != null) {
            TextView name = mHeadView.findViewById(R.id.name);
            TextView age = mHeadView.findViewById(R.id.age);
            TextView ward = mHeadView.findViewById(R.id.ward);
            TextView bedNo = mHeadView.findViewById(R.id.bedNo);
            name.setText("姓名:" + response.PatName);
            age.setText("年龄:" + response.Age);
            ward.setText("病区:" + response.ward);
            bedNo.setText(response.Bed + "床");
        }
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
    public void handlerCode(QRCodeEvent qrCodeEvent) {
        Log.d(TAG, "handlerCode: " + qrCodeEvent.code);
    }
}
