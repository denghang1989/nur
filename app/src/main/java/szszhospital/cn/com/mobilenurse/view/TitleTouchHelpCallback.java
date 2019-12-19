package szszhospital.cn.com.mobilenurse.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.github.florent37.viewanimator.ViewAnimator;

import java.util.Collections;
import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.TitleSheetAdapter;
import szszhospital.cn.com.mobilenurse.dialog.DataChangedCallback;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;

public class TitleTouchHelpCallback extends ItemTouchHelper.Callback {
    private TitleSheetAdapter                    mAdapter;
    private Context                              mContext;
    private DataChangedCallback<List<LocAccess>> mDataChangedCallback;
    private boolean                              canDrag  = true;
    private boolean                              canSwipe = true;

    public TitleTouchHelpCallback(Context context, TitleSheetAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int dragFlags = 0;
        int swipeFlags = 0;
        if (layoutManager instanceof GridLayoutManager) {
            // 如果是Grid布局，则不能滑动，只能上下左右拖动
            dragFlags =
                    ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            swipeFlags = 0;
        } else if (layoutManager instanceof LinearLayoutManager) {
            // 如果是纵向Linear布局，则能上下拖动，左右滑动
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                // 如果是横向Linear布局，则能左右拖动，上下滑动
                swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
        }
        return makeMovementFlags(dragFlags, swipeFlags); //该方法指定可进行的操作
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
        int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mAdapter.getData(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mAdapter.getData(), i, i - 1);
            }
        }
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        if (mDataChangedCallback != null) {
            mDataChangedCallback.onMove(mAdapter.getData());
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mDataChangedCallback != null) {
            mDataChangedCallback.onSwiped(mAdapter.getData());
        }
    }

    /**
     * 在这个回调中，如果返回true，表示可以触发长按拖动事件，false则表示不能
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return canDrag;
    }

    /**
     * 在这个回调中，如果返回true，表示可以触发滑动事件，false表示不能
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return canSwipe;
    }

    public void setCanDrag(boolean canDrag) {
        this.canDrag = canDrag;
    }

    public void setCanSwipe(boolean canSwipe) {
        this.canSwipe = canSwipe;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackground(mContext.getDrawable(R.drawable.bg_title_item_selected));
            ViewAnimator.animate(viewHolder.itemView).scale(1f, 1.2f).duration(250).start();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackground(mContext.getDrawable(R.drawable.bg_title_item));
        ViewAnimator.animate(viewHolder.itemView).scale(1.2f, 1f).duration(250).start();
    }

    public void setDataChangedCallback(DataChangedCallback<List<LocAccess>> dataChangedCallback) {
        mDataChangedCallback = dataChangedCallback;
    }
}
