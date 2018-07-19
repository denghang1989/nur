package szszhospital.cn.com.mobilenurse.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.reflect.Method;

import okhttp3.Call;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActiviyMainBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.fragemt.DialogInterface;
import szszhospital.cn.com.mobilenurse.fragemt.PatientListFragment;
import szszhospital.cn.com.mobilenurse.fragemt.UpdateDialogFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.MainContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.MainPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.UpdateApp;
import szszhospital.cn.com.mobilenurse.utils.AppUtil;

public class MainActivity extends BasePresentActivity<ActiviyMainBinding, MainPresenter> implements MainContract.View, DialogInterface {
    private static final String TAG = "MainActivity";
    private MainActivityAdapter  mAdapter;
    private UpdateDialogFragment mUpdateDialogFragment;
    private UpdateApp            mUpdateApp;

    @Override
    protected int getLayoutId() {
        return R.layout.activiy_main;
    }

    @Override
    protected void initView() {
        setSwipeBackEnable(false);
        mDataBinding.viewPager.setAdapter(mAdapter);
        mDataBinding.toolbar.setTitle(App.loginUser.UserName + "，您好！");
        loadRootFragment(R.id.patientList, PatientListFragment.newInstance());
        App.access.toolbarHandler(mDataBinding.drawerLayout);
        setSupportActionBar(mDataBinding.toolbar);
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new MainActivityAdapter(getSupportFragmentManager());
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> {
            App.access.openDrawer(mDataBinding.drawerLayout);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getUpdateApp();
    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void closeDrawer() {
        mDataBinding.drawerLayout.closeDrawer(Gravity.START);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectedPatient(SelectPatientEvent event) {
        if (App.patientInfo != null) {
            mDataBinding.patient.setText(App.patientInfo.DisBed + ":" + App.patientInfo.PAPMIName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tools_scan:
                break;
            case R.id.tools_patient_list:
                break;
            case R.id.tools_patient_calendar:
                break;
        }
        ToastUtils.showShort(item.getItemId() + "");
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDialog(UpdateApp updateApp) {
        if (updateApp.versionCode > AppUtil.getLocalVersion(this)) {
            mUpdateDialogFragment = (UpdateDialogFragment) getSupportFragmentManager().findFragmentByTag(UpdateDialogFragment.tag);
            if (mUpdateDialogFragment != null) {
                mUpdateDialogFragment.dismiss();
            }
            mUpdateDialogFragment = UpdateDialogFragment.newInstance(updateApp.desc);
            mUpdateDialogFragment.setDialogInterface(this);
            mUpdateDialogFragment.show(getSupportFragmentManager(), UpdateDialogFragment.tag);
            mUpdateApp = updateApp;
        }
    }

    @Override
    public void onPositive() {
        if (!StringUtils.isTrimEmpty(mUpdateApp.url)) {
            OkHttpUtils.get().url(mUpdateApp.url).build().
                    execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "App.apk") {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Intent intent = IntentUtils.getInstallAppIntent(response);
                            startActivity(intent);
                        }
                    });
        }
    }

    @Override
    public void onNegative() {

    }
}
