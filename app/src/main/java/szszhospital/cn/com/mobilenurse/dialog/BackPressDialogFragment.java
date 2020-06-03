package szszhospital.cn.com.mobilenurse.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.Logon;

public class BackPressDialogFragment extends DialogFragment {

    public static final String TAG = "BackPressDialogFragment";

    private Button          mCancel;
    private Button          mOk;
    private DialogInterface mDialogInterface;
    private Disposable mDisposable;

    public static BackPressDialogFragment newInstance() {
        return new BackPressDialogFragment();
    }

    public void setDialogInterface(DialogInterface dialogInterface) {
        mDialogInterface = dialogInterface;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_backpress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mCancel = view.findViewById(R.id.cancel);
        mOk = view.findViewById(R.id.ok);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogInterface != null) {
                    mDisposable = ApiService.Instance().getService().logon().
                            compose(RxUtil.httpHandleResponse()).
                            compose(RxUtil.rxSchedulerHelper()).
                            subscribe(new Consumer<Logon>() {
                                @Override
                                public void accept(Logon logon) throws Exception {
                                    mDialogInterface.onNegative();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    mDialogInterface.onNegative();
                                }
                            });

                }
                dismiss();
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogInterface != null) {
                    mDialogInterface.onPositive();
                }
                dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(), SizeUtils.dp2px(300));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
