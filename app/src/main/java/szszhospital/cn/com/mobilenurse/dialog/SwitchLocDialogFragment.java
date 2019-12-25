package szszhospital.cn.com.mobilenurse.dialog;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.LoginLocAdapter;
import szszhospital.cn.com.mobilenurse.event.SwitchLocEvent;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfo;

public class SwitchLocDialogFragment extends DialogFragment implements LoginLocAdapter.OnItemClickListener {
    public static final String TAG = "SwitchLocDialogFragment";

    private LoginLocAdapter mAdapter;

    public static SwitchLocDialogFragment newInstance() {
        return new SwitchLocDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_switch_loc,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mAdapter = new LoginLocAdapter(R.layout.item_login_spinner);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter.setOnItemClickListener(this);
        ApiService.Instance().getService().getLoginLoc(App.loginUser.UserDR)
                .compose(RxUtil.httpHandle())
                .compose(RxUtil.rxSchedulerHelper())
                .subscribe(new Observer<List<LocInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<LocInfo> locInfos) {
                        mAdapter.setNewData(locInfos);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight() * 2 / 3);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        LocInfo item = (LocInfo) adapter.getItem(position);
        EventBus.getDefault().post(new SwitchLocEvent(item.locId));
        App.loginUser.UserLoc = item.locId;
        dismiss();
    }
}
