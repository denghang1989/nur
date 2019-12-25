package szszhospital.cn.com.mobilenurse.dialog;

import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugAllergyAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseDialogFragment;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.DrugAllergy;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;

/**
 * 药敏结果
 */
public class DrugAllergyFragment extends BaseDialogFragment {
    public static final String TAG = "DrugAllergyFragment";

    private static final String DATA = "data";
    private LisOrderDetail     mLisOrderDetail;
    private Disposable         mDisposable;
    private RecyclerView       mRecyclerView;
    private DrugAllergyAdapter mAdapter;
    private Toolbar            mToolbar;

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
        mAdapter = new DrugAllergyAdapter(R.layout.item_drug_allergy);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drug_allergy;
    }

    @Override
    protected void initData() {
        mDisposable = ApiService.Instance().getService().getLisDrugAllergyData(mLisOrderDetail.ReportResultDR)
                .compose(RxUtil.httpHandleResponse())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Consumer<List<DrugAllergy>>() {
                    @Override
                    public void accept(List<DrugAllergy> drugAllergies) throws Exception {
                        mAdapter.setNewData(drugAllergies);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setTitle(mLisOrderDetail.Result);
    }

    @Override
    protected void initEvent() {
        mToolbar.setNavigationOnClickListener(view -> {
            dismiss();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
