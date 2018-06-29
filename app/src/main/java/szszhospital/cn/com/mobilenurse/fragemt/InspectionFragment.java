package szszhospital.cn.com.mobilenurse.fragemt;

import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentInspectionBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.InspectionPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.PacsOrderSubscribeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;

/**
 * 检查记录时间，运送病人检查科室
 * 记录 病人出病房的时间；
 * 记录 病人到达检查科室的时间
 */
public class InspectionFragment extends BasePresenterFragment<FragmentInspectionBinding, InspectionPresenter> implements InspectionContract.View {
    private static final String TAG = "InspectionFragment";
    private String                    mPreEpisodeID;
    private PacsOrderSubscribeRequest mRequest;
    private String[]                  mParam;

    @Override
    protected void init() {
        super.init();
        mRequest = new PacsOrderSubscribeRequest();
        mParam = new String[]{"","","","","","","","",""};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar lastDate = Calendar.getInstance();
        mParam[1] = dateFormat.format(lastDate.getTime());
        lastDate.roll(Calendar.DATE, -7);
        mParam[0] = dateFormat.format(lastDate.getTime());
    }

    @Override
    protected void initData() {
        super.initData();
        if (!StringUtils.isTrimEmpty(mPreEpisodeID)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mParam.length; i++) {
                sb.append(mParam[i]);
                if (i != mParam.length - 1) {
                    sb.append("^");
                }
            }
            mRequest.params = sb.toString();
            mPresenter.getPacsOrderList(mRequest);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_inspection;
    }

    @Override
    protected InspectionPresenter initPresenter() {
        return new InspectionPresenter();
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
            mPreEpisodeID = episodeID;
            ToastUtils.showShort(episodeID);
            mParam[5] = episodeID;
            // 获取病人预约检查信息
            initData();
        } else {
            // 处理包药机
            ToastUtils.showShort("病人检查时间记录模块，只能扫描病人腕带");
        }

    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }

    @Override
    public void showPacsOrderList(List<PacsOrderSubscribe.OrderSubscribe> list) {

    }


}
