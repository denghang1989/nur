package szszhospital.cn.com.mobilenurse.viewholder.lis;

import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import szszhospital.cn.com.mobilenurse.R;

public class ColumnHeaderViewHolder extends AbstractViewHolder {

    public final TextView mTextView;

    public ColumnHeaderViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.col);
    }

}
