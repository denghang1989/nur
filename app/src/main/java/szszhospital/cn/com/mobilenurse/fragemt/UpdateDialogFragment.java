package szszhospital.cn.com.mobilenurse.fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import szszhospital.cn.com.mobilenurse.R;

public class UpdateDialogFragment extends DialogFragment {
    public static final String tag = "UpdateDialogFragment";

    private DialogInterface mDialogInterface;
    private TextView        mTitle;
    private TextView        mContent;
    private Button          mCancel;
    private Button          mOk;

    public void setDialogInterface(DialogInterface dialogInterface) {
        mDialogInterface = dialogInterface;
    }

    public static UpdateDialogFragment newInstance() {
        return new UpdateDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = view.findViewById(R.id.title);
        mContent = view.findViewById(R.id.content);
        mCancel = view.findViewById(R.id.cancel);
        mOk = view.findViewById(R.id.ok);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mDialogInterface != null) {
            mCancel.setOnClickListener(v -> {
                mDialogInterface.onNegative();
                dismiss();
            });
            mOk.setOnClickListener(v -> mDialogInterface.onPositive());
        }
    }
}
