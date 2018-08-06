package szszhospital.cn.com.mobilenurse.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.entity.LisTableViewModel;
import szszhospital.cn.com.mobilenurse.entity.lisdetail.Cell;
import szszhospital.cn.com.mobilenurse.entity.lisdetail.ColumnHeader;
import szszhospital.cn.com.mobilenurse.entity.lisdetail.RowHeader;
import szszhospital.cn.com.mobilenurse.viewholder.lis.CellViewHolder;
import szszhospital.cn.com.mobilenurse.viewholder.lis.ColumnHeaderViewHolder;
import szszhospital.cn.com.mobilenurse.viewholder.lis.RowHeaderViewHolder;

public class LisTableAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader, Cell> {
    private static final String TAG = "LisTableAdapter";

    private       LisTableViewModel mTableViewModel;
    private final LayoutInflater    mInflater;

    public LisTableAdapter(Context context, LisTableViewModel tableViewModel) {
        super(context);
        this.mTableViewModel = tableViewModel;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }

    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View cellLayout = mInflater.inflate(R.layout.cell_data, parent, false);
        return new CellViewHolder(cellLayout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int columnPosition, int rowPosition) {
        Cell cell = (Cell) cellItemModel;
        CellViewHolder viewHolder = (CellViewHolder) holder;
        viewHolder.mTextView.setText(cell.getData());
        if (cell.isFlag()) {
            switch (cell.getData()) {
                case "L":
                    viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.blue));
                    break;
                case "A":
                case "H":
                    viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.red));
                    break;
                default:
                    viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.gray));
                    break;
            }
        } else {
            viewHolder.mTextView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }

    @Override
    public AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View colHeaderLayout = mInflater.inflate(R.layout.cell_col_head, parent, false);
        return new ColumnHeaderViewHolder(colHeaderLayout);
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel, int columnPosition) {
        ColumnHeaderViewHolder viewHolder = (ColumnHeaderViewHolder) holder;
        ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;
        viewHolder.mTextView.setText(columnHeader.getData());
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {
        View rowHeaderLayout = mInflater.inflate(R.layout.cell_row_head, parent, false);
        return new RowHeaderViewHolder(rowHeaderLayout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int rowPosition) {
        RowHeaderViewHolder viewHolder = (RowHeaderViewHolder) holder;
        RowHeader rowHeader = (RowHeader) rowHeaderItemModel;
        viewHolder.mTextView.setText(rowHeader.getData());
    }

    @Override
    public View onCreateCornerView() {
        View corner = mInflater.inflate(R.layout.table_view_corner_layout, null);
        return corner;
    }
}
