package szszhospital.cn.com.mobilenurse.viewholder.lis;

import android.view.View;
import android.widget.TextView;

import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import szszhospital.cn.com.mobilenurse.R;

public class CellViewHolder extends AbstractViewHolder {

    public final TextView mTextView;

    public CellViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.cell);
    }
}
