package szszhospital.cn.com.mobilenurse.clear;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.DragPhotoActivity;
import szszhospital.cn.com.mobilenurse.adapter.EMRImageAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentEmrImageBinding;
import szszhospital.cn.com.mobilenurse.event.SelectEmrRecordEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRImageContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.EMRImagePresenter;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

public class EMRImageFragment extends BasePresenterFragment<FragmentEmrImageBinding, EMRImagePresenter> implements EMRImageContract.View {
    private static final String KEY_DATA = "data";
    private EMRImageAdapter mAdapter;
    private EMREposideInfo  mEmrEposideInfo;

    public static EMRImageFragment newInstance() {
        return new EMRImageFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new EMRImageAdapter(R.layout.item_emr_image);
        mEmrEposideInfo = getArguments().getParcelable(KEY_DATA);
    }

    @Override
    protected void initData() {
        super.initData();
        if (mEmrEposideInfo != null && App.patientInfo != null) {
            mPresenter.getEMRImageInfoList(App.patientInfo.EpisodeID, mEmrEposideInfo.InternalID);
        } else {
            mAdapter.clear();
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mDataBinding.listView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.listView.setAdapter(mAdapter);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(_mActivity, DragPhotoActivity.class);
                intent.putExtra(KEY_DATA, mAdapter.getItem(position));
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, "emr").toBundle());
            }
        });
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
    public void selectEmrRecordInfo(SelectEmrRecordEvent event) {
        mEmrEposideInfo = event.data;
        initData();
    }

    @Override
    protected EMRImagePresenter initPresenter() {
        return new EMRImagePresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_emr_image;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showEMRList(List<EMRImageInfo> list) {
        if (list != null && list.size() > 0) {
            List<String> urLs = list.get(0).ImagePathURLs;
            mAdapter.setNewData(urLs);
        }
    }

    @Override
    public void refresh() {

    }
}
