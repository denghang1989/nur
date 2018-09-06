package szszhospital.cn.com.mobilenurse.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.StringUtils;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.OrderListAdapter;
import szszhospital.cn.com.mobilenurse.remote.response.Order;

public class OrderItemDecoration extends RecyclerView.ItemDecoration {

    private OrderListAdapter mAdapter;
    private Context          mContext;
    private Paint            mPaint;
    private Rect mBounds = new Rect();

    public OrderItemDecoration(OrderListAdapter adapter, Context context) {
        mAdapter = adapter;
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.black));
        mPaint.setStrokeWidth(ConvertUtils.dp2px(2));
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            int childWidth = child.getWidth();
            int childHeight = child.getHeight();
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            int position = parent.getLayoutManager().getPosition(child);
            if (position == 0) {
                if (mAdapter.getData().size() > 1) {
                    Order current = mAdapter.getItem(position);
                    Order next = mAdapter.getItem(position + 1);
                    if (StringUtils.isTrimEmpty(current.SeqNo) || StringUtils.isTrimEmpty(next.SeqNo)) {
                        return;
                    }
                    // 相同的报告单（第一条记录）
                    if (StringUtils.equals(current.SeqNo, next.SeqNo)) {
                        drawHLine(c, childHeight);
                        drawVTop2Line(c, childHeight);
                    }
                }
            } else {
                if (position == mAdapter.getData().size() - 1) {
                    //最后一个节点
                    Order pre = mAdapter.getItem(position - 1);
                    Order current = mAdapter.getItem(position);
                    if (StringUtils.isTrimEmpty(current.SeqNo) || StringUtils.isTrimEmpty(pre.SeqNo)) {
                        return;
                    }
                    //中间节点画竖线
                    if (StringUtils.equals(current.SeqNo, pre.SeqNo)) {
                        drawHLine(c, childHeight);
                        drawVEnd2Line(c, childHeight);
                    }
                } else {
                    //中间一个节点
                    Order pre = mAdapter.getItem(position - 1);
                    Order current = mAdapter.getItem(position);
                    Order next = mAdapter.getItem(position + 1);
                    if (StringUtils.isTrimEmpty(current.SeqNo) || StringUtils.isTrimEmpty(next.SeqNo)||StringUtils.isTrimEmpty(pre.SeqNo)) {
                        return;
                    }
                    //中间节点画竖线
                    if (StringUtils.equals(current.SeqNo, pre.SeqNo) && StringUtils.equals(current.SeqNo, next.SeqNo)) {
                        drawVLine(c);
                    }

                    //头节点
                    if ((!StringUtils.equals(current.SeqNo, pre.SeqNo)) && StringUtils.equals(current.SeqNo, next.SeqNo)) {
                        drawHLine(c, childHeight);
                        drawVTop2Line(c, childHeight);
                    }

                    //尾节点
                    if (StringUtils.equals(current.SeqNo, pre.SeqNo) && (!StringUtils.equals(current.SeqNo, next.SeqNo))) {
                        drawHLine(c, childHeight);
                        drawVEnd2Line(c, childHeight);
                    }
                }
            }
        }
    }

    private void drawVEnd2Line(Canvas c, int childHeight) {
        int startX = getStartX();
        int startY = getTopY();
        int endX = getStartX();
        int endY = getTopY() + childHeight / 2;
        c.drawLine(startX, startY, endX, endY, mPaint);
    }

    private int getTopY() {
        return mBounds.top;
    }

    private int getStartX() {
        return ConvertUtils.dp2px(4);
    }


    private int getEndX() {
        return ConvertUtils.dp2px(10);
    }

    private void drawVTop2Line(Canvas c, int childHeight) {
        int startX = getStartX();
        int startY = getTopY() + childHeight / 2;
        int endX = getStartX();
        int endY = mBounds.bottom;
        c.drawLine(startX, startY, endX, endY, mPaint);
    }

    private void drawVLine(Canvas c) {
        int startX = getStartX();
        int startY = getTopY();
        int endX = getStartX();
        int endY = mBounds.bottom;
        c.drawLine(startX, startY, endX, endY, mPaint);
    }

    private void drawHLine(Canvas c, int childHeight) {
        int startX = getStartX();
        int startY = getTopY() + childHeight / 2;
        int endX = getEndX();
        int endY = getTopY() + childHeight / 2;
        c.drawLine(startX, startY, endX, endY, mPaint);
    }
}
