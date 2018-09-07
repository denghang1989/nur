package szszhospital.cn.com.mobilenurse.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;

import szszhospital.cn.com.mobilenurse.R;

public class BackPressDialogFragment extends DialogFragment {

    public static final String TAG = "BackPressDialogFragment";

    private Button          mCancel;
    private Button          mOk;
    private DialogInterface mDialogInterface;

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
                    mDialogInterface.onNegative();
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
}
