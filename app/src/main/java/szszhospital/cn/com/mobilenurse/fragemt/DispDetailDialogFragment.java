package szszhospital.cn.com.mobilenurse.fragemt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.DrugListAdapter;
import szszhospital.cn.com.mobilenurse.mvp.contract.DispDetailListContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.DispDetailListPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.DispDetailListRequest;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

public class DispDetailDialogFragment extends BottomSheetDialogFragment implements DispDetailListContract.View {

    private static final String KEY_AUDITDR = "AuditDr";
    private Activity              mActivity;
    private DispDetailListRequest mRequest;
    private RecyclerView          mRecyclerView;
    private Button                mOk;
    private Button                mCancel;

    private DispDetailListPresenter mPresenter;
    private View                    mHeadView;
    private DrugListAdapter         mAdapter;
    private DialogInterface         mDialogInterface;

    private String okText;

    public static DispDetailDialogFragment newInstance(String auditdr) {
        Bundle args = new Bundle();
        args.putString(KEY_AUDITDR, auditdr);
        DispDetailDialogFragment fragment = new DispDetailDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        mPresenter = new DispDetailListPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequest = new DispDetailListRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        mDialogInterface = dialogInterface;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView_dialog);
        mOk = view.findViewById(R.id.ok);
        if (!StringUtils.isTrimEmpty(okText)) {
            mOk.setText(okText);
        }
        mCancel = view.findViewById(R.id.cancel);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new DrugListAdapter(R.layout.item_drug);
        mRecyclerView.setAdapter(mAdapter);
        mHeadView = LayoutInflater.from(mActivity).inflate(R.layout.item_drug_head, null);
        mAdapter.addHeaderView(mHeadView);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRequest.AuditDr = getArguments().getString(KEY_AUDITDR);
        mPresenter.getDispDetailList(mRequest);

        mCancel.setOnClickListener(v -> {
            if (mDialogInterface != null) {
                mDialogInterface.onNegative();
            }
        });
        mOk.setOnClickListener(v -> {
            if (mDialogInterface != null) {
                mDialogInterface.onPositive();
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight() * 2 / 3);
    }

    @Override
    public void setDispDetailList(List<DispDetailResponse> list) {
        mAdapter.setNewData(list);
        if (list != null && list.size() > 0) {
            setHeadViewData(list.get(0));
        }
    }

    private void setHeadViewData(DispDetailResponse response) {
        if (mHeadView != null) {
            TextView name = mHeadView.findViewById(R.id.name);
            TextView age = mHeadView.findViewById(R.id.age);
            TextView ward = mHeadView.findViewById(R.id.ward);
            TextView bedNo = mHeadView.findViewById(R.id.bedNo);
            ImageView sex = mHeadView.findViewById(R.id.sex);
            name.setText("姓名:" + response.PatName);
            age.setText("年龄:" + response.Age);
            ward.setText("病区:" + response.ward);
            if (response.Bed.contains("床")) {
                bedNo.setText(response.Bed);
            } else {
                bedNo.setText(response.Bed + "床");
            }
            if (StringUtils.equals(response.Sex, "男")) {
                sex.setImageResource(R.drawable.icon_man);
            } else {
                sex.setImageResource(R.drawable.icon_woman);
            }
        }
    }

    public void setOkText(String text) {
        if (!StringUtils.isTrimEmpty(text)) {
            okText = text;
        }
    }
}
