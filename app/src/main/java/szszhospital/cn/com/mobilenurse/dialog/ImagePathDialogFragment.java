package szszhospital.cn.com.mobilenurse.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import szszhospital.cn.com.mobilenurse.R;

public class ImagePathDialogFragment extends BaseFullBottomSheetFragment {
    public static final String Key = "ImagePathDialogFragment";
    private RecyclerView mRecycleView;

    public static ImagePathDialogFragment getInstance() {
        return new ImagePathDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_path,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView = view.findViewById(R.id.recyclerView);
        mRecycleView.setLayoutManager(new GridLayoutManager(getContext(),4));
    }
}
