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

        helper.setVisible(R.id.unRegister, false);
        helper.setVisible(R.id.icon, true);
        helper.setVisible(R.id.photo, true);

        if (!StringUtils.isTrimEmpty(item.Memo)) {
            if (item.Memo.contains("S")) {
                if (StringUtils.isTrimEmpty(item.TPdfPath) || AppUtil.IsUrl(path)) {
                    helper.setVisible(R.id.icon, true);
                }
            } else {
                helper.setVisible(R.id.icon, false);
            }
        } else {
            helper.setVisible(R.id.icon, false);
        }

        // 病理
        if (StringUtils.equals(item.TreplocDr, "13")) {
            helper.setVisible(R.id.icon, true);
        }

        // CT室
        if (StringUtils.equals(item.TreplocDr, "11")) {
            if ((item.TStudyNo != null) && (!item.TStudyNo.contains("A"))) {
                helper.setVisible(R.id.unRegister, true);
                helper.setVisible(R.id.icon, false);
                helper.setVisible(R.id.photo, false);
            }
        }

        //心电图屏蔽图像标签
        if (StringUtils.equals(item.TreplocDr, "15") || StringUtils.equals(item.TreplocDr, "16")) {
            helper.setVisible(R.id.photo, false);
        }

        if (StringUtils.isTrimEmpty(item.TStudyNo)) {
            helper.setVisible(R.id.icon, false);
            helper.setVisible(R.id.photo, false);
            helper.setVisible(R.id.unRegister, true);
            helper.setText(R.id.unRegister,"无数据");
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
