package szszhospital.cn.com.mobilenurse.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.view.menu.MenuBuilder;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.StringUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.reflect.Method;

import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActiviyMainBinding;
import szszhospital.cn.com.mobilenurse.dialog.BackPressDialogFragment;
import szszhospital.cn.com.mobilenurse.dialog.DialogInterface;
import szszhospital.cn.com.mobilenurse.dialog.SwitchLocDialogFragment;
import szszhospital.cn.com.mobilenurse.dialog.UpdateDialogFragment;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.fragemt.PatientListFragment;
import szszhospital.cn.com.mobilenurse.mvp.contract.MainContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.MainPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.UpdateApp;
import szszhospital.cn.com.mobilenurse.utils.FileCallback;
import szszhospital.cn.com.mobilenurse.utils.FileDownUtil;

/**
 * @author admin
 */
public class MainActivity extends BasePresentActivity<ActiviyMainBinding, MainPresenter> implements MainContract.View, DialogInterface {
    private static final String                  TAG = "MainActivity";
    private              MainActivityAdapter     mAdapter;
    private              UpdateApp               mUpdateApp;
    private              UpdateDialogFragment    mUpdateDialogFragment;
    private              BackPressDialogFragment mBackPressDialogFragment;
    private              SwitchLocDialogFragment mSwitchLocDialogFragment;

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
        setSupportActionBar(mDataBinding.toolbar);
    }

    @Override
    protected void init() {
        super.init();
        mAdapter = new MainActivityAdapter(getSupportFragmentManager());
        EventBus.getDefault().register(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions();
        }
    }

    private void requestPermissions() {
        final RxPermissions rxPermissions = new RxPermissions(this);
        Disposable subscribe = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {

                    } else {

                    }
                });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> {
            mDataBinding.drawerLayout.openDrawer(Gravity.START);
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getUpdateApp();
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
                ScanQRCodeActivity.startScanQRCodeActivityForResult(this);
                break;
            case R.id.tools_search:
            case R.id.tools_patient_list:
                SearchActivity.startSearchActivity(this);
                break;
            case R.id.tools_toggle_locId:
                showSwitchLocDialog();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showDialog(UpdateApp updateApp) {
        if (updateApp.versionCode > AppUtils.getAppVersionCode()) {
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
            App.getAsynHandler().post(() -> {
                FileDownUtil.downFile(mUpdateApp.url, new FileCallback() {
                    @Override
                    public void success(File file) {
                        Intent intent = IntentUtils.getInstallAppIntent(file);
                        startActivity(intent);
                    }

                    @Override
                    public void error(Exception e) {

                    }
                });
            });
        }
    }

    @Override
    public void onNegative() {

    }

    @Override
    public void onBackPressedSupport() {
        mBackPressDialogFragment = (BackPressDialogFragment) getSupportFragmentManager().findFragmentByTag(BackPressDialogFragment.TAG);
        if (mBackPressDialogFragment != null) {
            mBackPressDialogFragment.dismiss();
        }

        mBackPressDialogFragment = BackPressDialogFragment.newInstance();
        mBackPressDialogFragment.setDialogInterface(new DialogInterface() {
            @Override
            public void onPositive() {
                finish();
            }

            @Override
            public void onNegative() {

            }
        });
        mBackPressDialogFragment.show(getSupportFragmentManager(), BackPressDialogFragment.TAG);
    }

    private void showSwitchLocDialog() {
        mSwitchLocDialogFragment = (SwitchLocDialogFragment) getSupportFragmentManager().findFragmentByTag(SwitchLocDialogFragment.TAG);
        if (mSwitchLocDialogFragment != null) {
            mSwitchLocDialogFragment.dismiss();
        }
        mSwitchLocDialogFragment = SwitchLocDialogFragment.newInstance();
        mSwitchLocDialogFragment.show(getSupportFragmentManager(), SwitchLocDialogFragment.TAG);
    }

    @Override
    public void showProgress() {
        mDataBinding.progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mDataBinding.progress.setVisibility(View.GONE);
    }
}
