package szszhospital.cn.com.mobilenurse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.entity.LocTable;

public class LoginSpinnerAdapter extends BaseAdapter {

    private List<LocTable> mLocList = null;
    private LayoutInflater mInflater;

    public LoginSpinnerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<LocTable> locs) {
        mLocList = locs;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLocList != null ? mLocList.size() : 0;
    }

    @Override
    public LocTable getItem(int position) {
        return mLocList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_login_spinner, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.item = convertView.findViewById(R.id.item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item.setText(mLocList.get(position).LocDesc.trim());
        return convertView;
    }


    private static class ViewHolder {
        public TextView item;
    }
}
