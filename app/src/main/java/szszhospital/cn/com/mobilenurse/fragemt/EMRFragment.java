package szszhospital.cn.com.mobilenurse.fragemt;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.activity.DragPhotoActivity;
import szszhospital.cn.com.mobilenurse.adapter.EMRAdapter;
import szszhospital.cn.com.mobilenurse.adapter.EMRImageAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentEmrBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.EMRPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;

/**
 * 电子病历
 */
public class EMRFragment extends BaseDoctorFragment<FragmentEmrBinding, EMRPresenter> implements EMRContract.View {

    private EMRAdapter      mAdapter;
    private EMRImageAdapter mEMRImageAdapter;
    private static final String KEY_DATA = "data";
    private static final String TAG      = "EMRFragment";
    private LinearLayoutManager mEMRLayoutManager;

    public static EMRFragment newInstance() {
        return new EMRFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new EMRAdapter(R.layout.item_emr_record);
        mEMRImageAdapter = new EMRImageAdapter(R.layout.item_emr_image);
    }

    @Override
    protected void initView() {
        super.initView();
        initMenu();
        initEMR();
    }

    private void initEMR() {
        mEMRLayoutManager = new LinearLayoutManager(_mActivity);
        mDataBinding.emr.setLayoutManager(mEMRLayoutManager);
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.emr.setAdapter(mEMRImageAdapter);
    }

    private void initMenu() {
        mDataBinding.listView.setLayoutManager(new LinearLayoutManager(_mActivity));
        mDataBinding.listView.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        mDataBinding.listView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            mPresenter.getEMREposideList(App.patientInfo.EpisodeID);
        }
    }

    @Override
    protected EMRPresenter initPresenter() {
        return new EMRPresenter();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            mAdapter.setSelectPosition(position);
            mAdapter.notifyDataSetChanged();
            EMREposideInfo item = mAdapter.getItem(position);
            mPresenter.getEMRImageInfoList(App.patientInfo.EpisodeID, item.InternalID);
            mEMRLayoutManager.scrollToPosition(0);
        });

        mEMRImageAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            startDragPhotoActivity(view, position);
        });

        mDataBinding.show.setOnClickListener(v -> {
            if (mDataBinding.listView.getVisibility() == View.VISIBLE) {
                mDataBinding.listView.setVisibility(View.GONE);
                mDataBinding.show.setText("显示");
            } else {
                mDataBinding.listView.setVisibility(View.VISIBLE);
                mDataBinding.show.setText("隐藏");
            }
        });
    }

    private void startDragPhotoActivity(View view, int position) {
        Intent intent = new Intent(_mActivity, DragPhotoActivity.class);
        intent.putExtra(KEY_DATA, mEMRImageAdapter.getItem(position));
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(_mActivity, view, "emr").toBundle());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_emr;
    }

    @Override
    public void showProgress() {
        mDataBinding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMenuList(List<EMREposideInfo> list) {
        if (list.size() > 0) {
            mAdapter.setSelectPosition(0);
            EMREposideInfo item = list.get(0);
            mPresenter.getEMRImageInfoList(App.patientInfo.EpisodeID, item.InternalID);
        } else {
            mAdapter.setSelectPosition(-1);
            mEMRImageAdapter.clear();
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
    }

    @Override
    public void showEMRList(List<EMRImageInfo> list) {
        if (list != null && list.size() > 0) {
            List<String> urLs = list.get(0).ImagePathURLs;
            mEMRImageAdapter.setNewData(urLs);
        }
    }


}
