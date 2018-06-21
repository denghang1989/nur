package szszhospital.cn.com.mobilenurse.adapter;

import android.graphics.Color;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;

/**
 * 2018/5/19 16  ""-未配药;A-已配;C-核对;T-交接;R-接收
 */
public class DrugListAdapter extends BaseQuickAdapter<DispDetailResponse, BaseViewHolder> {
    private static final String TAG = "DrugListAdapter";

    public DrugListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DispDetailResponse item) {
        helper.setText(R.id.drugName, item.InciDesc)
                .setText(R.id.dispQty, "数量：" + item.DispQty + "   规格：" + item.Spec)
                .setText(R.id.InciCode, "药品编码：" + item.InciCode)
                .setText(R.id.dateTime, "用药时间：" + item.DosDateTime);
        switch (item.ConFirmFlag) {
            case "":
                helper.setVisible(R.id.icon_flag, false);
                break;
            case "A":
                helper.setVisible(R.id.icon_flag, true);
                break;
        }
        int flag = getDrugNumberFlag(item);
        switch (flag) {
            case 0: //整包
                helper.setBackgroundColor(R.id.background, Color.parseColor("#00000000"));
                break;
            case 1: //非整包
                helper.setBackgroundColor(R.id.background, Color.parseColor("#8DEEEE"));
                break;
        }
        //TextView drugName = helper.getView(R.id.drugName);
        //drugName.setSelected(true);
    }

    private int getDrugNumberFlag(DispDetailResponse item) {
        int flag = 0;
        // 判断是否是整包装
        if (item.Spec != null && item.Spec.contains("*")) {
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(item.Spec.substring(item.Spec.indexOf("*")));
            String number = m.replaceAll("").trim();
            if (TextUtils.isDigitsOnly(number) && TextUtils.isDigitsOnly(item.DispQty)) {
                int dispQty = Integer.parseInt(item.DispQty);
                int spec = Integer.parseInt(number);
                if (dispQty % spec == 0) {
                    flag = 0;
                } else {
                    flag = 1;
                }
            }
        }
        return flag;
    }
}
