package szszhospital.cn.com.mobilenurse.adapter;

import com.blankj.utilcode.util.StringUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.AppUtil;

public class PacsResultAdapter extends BaseQuickAdapter<PacsOrder, BaseViewHolder> {

    public PacsResultAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PacsOrder item) {
        String path = getPath(item);
        helper.setText(R.id.dateTime, item.TItemDate)
                .setText(R.id.name_order, item.TItemName)
                .setText(R.id.loc_order, item.TLocName)
                .addOnClickListener(R.id.icon)
                .addOnClickListener(R.id.photo);
        if (!StringUtils.isTrimEmpty(item.Memo)) {
            if (StringUtils.equals("S^已发布", item.Memo)) {
                if (StringUtils.isTrimEmpty(item.TPdfPath) || AppUtil.IsUrl(path)) {
                    helper.setVisible(R.id.icon, true);
                }
            }
        } else {
            helper.setVisible(R.id.icon, false);
        }
        // 病理
        if (StringUtils.equals(item.TreplocDr, "13")) {
            helper.setVisible(R.id.icon, true);
        }
    }

    public String getPath(PacsOrder item) {
        String path = "";
        String tReplocpath = item.TReplocpath;
        if (!StringUtils.isTrimEmpty(tReplocpath)) {
            String[] split = tReplocpath.split("\\^");
            path = split[0];
        }
        return path;
    }

}
