package szszhospital.cn.com.mobilenurse.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.OrderExtAdapter;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.Order;
import szszhospital.cn.com.mobilenurse.remote.response.OrderExecuteInfo;

public class OrderExtDialogFragment extends DialogFragment {
    public static final  String TAG  = "OrderExtDialogFragment";
    private static final String DATA = "data";
    private RecyclerView    mListView;
    private Order           mOrder;
    private Toolbar         mToolbar;
    private ProgressBar     mProgressBar;
    private OrderExtAdapter mAdapter;

    public static OrderExtDialogFragment newInstance(Order order) {
        Bundle args = new Bundle();
        args.putParcelable(DATA, order);
        OrderExtDialogFragment fragment = new OrderExtDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrder = getArguments().getParcelable(DATA);
        mAdapter = new OrderExtAdapter(R.layout.item_order_ext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = view.findViewById(R.id.list);
        mToolbar = view.findViewById(R.id.toolbar);
        mProgressBar = view.findViewById(R.id.progressBar);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mListView.setAdapter(mAdapter);
        mToolbar.setTitle(mOrder.ArcimDesc);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        mToolbar.setNavigationOnClickListener(v -> dismiss());
    }

    private void initData() {
        mProgressBar.setVisibility(View.VISIBLE);
        ApiService.Instance().getService().getOrderExecuteList(mOrder.OEItemID)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.httpHandleResponse())
                .subscribe(new Observer<List<OrderExecuteInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<OrderExecuteInfo> orderExecuteInfos) {
                        Collections.sort(orderExecuteInfos, (o1, o2) -> {
                            Long order1DateTime = TimeUtils.string2Millis(o1.TExStDate);
                            Long order2DateTime = TimeUtils.string2Millis(o2.TExStDate);
                            return (int) (order2DateTime - order1DateTime);
                        });
                        mAdapter.setNewData(orderExecuteInfos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight() * 2 / 3);
    }
}
