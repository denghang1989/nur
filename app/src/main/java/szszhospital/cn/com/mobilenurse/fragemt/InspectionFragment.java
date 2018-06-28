package szszhospital.cn.com.mobilenurse.fragemt;

import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;

/**
 * 检查记录时间，运送病人检查科室
 * 记录 病人出病房的时间；
 * 记录 病人到达检查科室的时间
 */
public class InspectionFragment extends BasePresenterFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inspection;
    }

    public static InspectionFragment newInstance() {
        return new InspectionFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlerCode(QRCodeEvent event) {
        String code = event.code;
        if (code.startsWith("WB")) {
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(code);
            String episodeID = m.replaceAll("").trim();
            ToastUtils.showShort(episodeID);
            // 获取病人预约检查信息
        } else {
            // 处理包药机
            ToastUtils.showShort("病人检查时间记录模块，只能扫描病人腕带");
        }

    }
}
