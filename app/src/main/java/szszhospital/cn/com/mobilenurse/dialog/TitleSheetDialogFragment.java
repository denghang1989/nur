package szszhospital.cn.com.mobilenurse.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.TitleSheetAdapter;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse_Table;
import szszhospital.cn.com.mobilenurse.utils.OnRecyclerItemClickListener;
import szszhospital.cn.com.mobilenurse.view.TitleTouchHelpCallback;

public class TitleSheetDialogFragment extends BaseFullBottomSheetFragment implements DataChangedCallback<List<LocAccessResponse>> {
    private static final String                 DATA = "data";
    private              RecyclerView           mRecyclerView;
    private              Activity               _mActivity;
    private              String                 mUserLoc;
    private              TitleSheetAdapter      mAdapter;
    private              ItemTouchHelper        mItemTouchHelper;
    private              TitleTouchHelpCallback mHelpCallback;

    public static TitleSheetDialogFragment newInstance(int[] points) {
        Bundle args = new Bundle();
        args.putIntArray(DATA, points);
        TitleSheetDialogFragment fragment = new TitleSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        int[] points = arguments.getIntArray(DATA);
        if (points != null) {
            setTopOffset(points[1]);
        }
        mUserLoc = App.loginUser.UserLoc;
        mAdapter = new TitleSheetAdapter(R.layout.item_title);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_title, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(_mActivity, 4));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initEvent();
    }

    private void initEvent() {
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView) {
            //item 点击事件
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
            }

            //item 长点击事件
            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                if (vh.getLayoutPosition() != mAdapter.getData().size() - 1) {
                    mItemTouchHelper.startDrag(vh);
                }
            }
        });
    }

    private void initData() {
        List<LocAccessResponse> locAccess = new Select().from(LocAccessResponse.class).where(LocAccessResponse_Table.LocId.eq(mUserLoc)).orderBy(LocAccessResponse_Table.position,true).queryList();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(locAccess);
        mHelpCallback = new TitleTouchHelpCallback(_mActivity, mAdapter);
        mHelpCallback.setDataChangedCallback(this);
        mItemTouchHelper = new ItemTouchHelper(mHelpCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onMove(List<LocAccessResponse> data) {
        for (int i = 0; i < data.size(); i++) {
            LocAccessResponse locAccessResponse = data.get(i);
            locAccessResponse.position = i;
            locAccessResponse.save();
        }
    }

    @Override
    public void onSwiped(List<LocAccessResponse> data) {

    }
}
