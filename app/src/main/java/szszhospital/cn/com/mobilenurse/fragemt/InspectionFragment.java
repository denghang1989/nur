package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.InspectionAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentInspectionBinding;
import szszhospital.cn.com.mobilenurse.event.QRCodeEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.InspectionContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.InspectionPresenter;
import szszhospital.cn.com.mobilenurse.remote.request.InspectionRequest;
import szszhospital.cn.com.mobilenurse.remote.request.PacsOrderSubscribeRequest;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;
import szszhospital.cn.com.mobilenurse.viewholder.InspectionViewHolder;

/**
 * 检查记录时间，运送病人检查科室
 * 记录 病人出病房的时间；
 * 记录 病人到达检查科室的时间
 */
public class InspectionFragment extends BasePresenterFragment<FragmentInspectionBinding, InspectionPresenter> implements InspectionContract.View, MaterialDialog.SingleButtonCallback {
    private static final String TAG = "InspectionFragment";
    private PacsOrderSubscribeRequest         mRequest;
    private String[]                          mParam;
    private InspectionAdapter                 mAdapter;
    private InspectionViewHolder              mViewHolder;
    private MaterialDialog                    mDialog;
    private InspectionRequest                 mInspection;
    private String                            mStatus;
    private PacsOrderSubscribe.OrderSubscribe mOrderSubscribe;

    @Override
    protected void init() {
        super.init();
        initParams();
        mRequest = new PacsOrderSubscribeRequest();
        mInspection = new InspectionRequest();
        mAdapter = new InspectionAdapter(R.layout.item_inspection);
        mViewHolder = new InspectionViewHolder(_mActivity, R.layout.inspection_head_view);
    }

    private void initParams() {
        mParam = new String[]{"", "", "", "", "", "", "", "", ""};
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        mParam[1] = dateFormat.format(new Date());
        Calendar lastDate = Calendar.getInstance();
        lastDate.add(Calendar.DATE, -7);
        mParam[0] = dateFormat.format(lastDate.getTime());
    }

    @Override
    protected void initData() {
        if (!StringUtils.isTrimEmpty(mParam[5])) {
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
    protected void initView() {
        super.initView();
        mDataBinding.list.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.list.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mAdapter.addHeaderView(mViewHolder.getConvertView());
        mDataBinding.list.setAdapter(mAdapter);
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
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> createLog(position));

        mAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                return true;
            }
        });
    }

    private void createLog(int position) {
        mOrderSubscribe = mAdapter.getItem(position);
        String content = "";
        if (!StringUtils.isTrimEmpty(mOrderSubscribe.transportStatus)) {
            switch (mOrderSubscribe.transportStatus) {
                case "A":
                    content = "检查完成，回病房！";
                    mStatus = "B";
                    break;
                case "B":
                    content = "病人到达病房！";
                    mStatus = "C";
                    break;
            }
        } else {
            content = "去检查，离开病房！";
            mStatus = "A";
        }
        mInspection.EpisodeID = mOrderSubscribe.PatNo;
        mInspection.Status = mStatus;
        mInspection.UserID = App.loginUser.UserDR;
        mInspection.ARRepID = mOrderSubscribe.arRepID;
        mInspection.Action = "save";
        createMaterialDialog(content);
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
            String episodeID = getNumFromString(code);
            mParam[5] = episodeID;
            // 获取病人预约检查信息
            initData();
        } else {
            // 处理包药机
            ToastUtils.showShort("病人检查时间记录模块，只能扫描病人腕带");
        }
    }

    @NonNull
    private String getNumFromString(String code) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(code);
        return m.replaceAll("").trim();
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
        mAdapter.setNewData(list);
        if (list.size() > 0) {
            mViewHolder.setData(list.get(0));
        }
    }


    private MaterialDialog createMaterialDialog(String content) {
        if (mDialog != null) {
            mDialog.dismiss();
        }
        return new MaterialDialog.Builder(_mActivity)
                .title(R.string.title)
                .content(content)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .onPositive(this)
                .onNegative(this)
                .show();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        if (which == DialogAction.POSITIVE) {
            switch (mOrderSubscribe.transportStatus) {
                case "":
                case "A":
                case "B":
                    mPresenter.saveOrUpdateLog(mInspection);
                    break;
                default:
                    break;
            }
        }
    }
}
