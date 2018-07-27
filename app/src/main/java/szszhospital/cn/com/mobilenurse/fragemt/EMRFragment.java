package szszhospital.cn.com.mobilenurse.fragemt;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.EMRAdapter;
import szszhospital.cn.com.mobilenurse.databinding.FragmentEmrBinding;
import szszhospital.cn.com.mobilenurse.event.SelectEmrRecordEvent;
import szszhospital.cn.com.mobilenurse.mvp.contract.EMRContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.EMRPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;

/**
 * 电子病历
 */
public class EMRFragment extends BaseDoctorFragment<FragmentEmrBinding, EMRPresenter> implements EMRContract.View {

    private EMRAdapter mAdapter;

    public static EMRFragment newInstance() {
        return new EMRFragment();
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new EMRAdapter(R.layout.item_emr_record);
    }

    @Override
    protected void initView() {
        super.initView();
        loadRootFragment(R.id.frameLayout_container, EMRImageFragment.newInstance());
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
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setSelectPosition(position);
                mAdapter.notifyDataSetChanged();
                EMREposideInfo item = mAdapter.getItem(position);
                EventBus.getDefault().post(new SelectEmrRecordEvent(item));
            }
        });

        mDataBinding.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataBinding.listView.getVisibility() == View.VISIBLE) {
                    mDataBinding.listView.setVisibility(View.GONE);
                    mDataBinding.show.setText("显示");
                } else {
                    mDataBinding.listView.setVisibility(View.VISIBLE);
                    mDataBinding.show.setText("隐藏");
                }
            }
        });
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
    public void showEMRList(List<EMREposideInfo> list) {
        if (list.size() > 0) {
            mAdapter.setSelectPosition(0);
            EventBus.getDefault().post(new SelectEmrRecordEvent(list.get(0)));
        } else {
            mAdapter.setSelectPosition(-1);
            EventBus.getDefault().post(new SelectEmrRecordEvent(null));
        }
        mAdapter.setNewData(list);
    }

    @Override
    public void refresh() {
        initData();
    }


}
