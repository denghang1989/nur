package szszhospital.cn.com.mobilenurse.activity;

import android.support.v7.view.menu.MenuBuilder;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.adapter.MainActivityAdapter;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActiviyMainBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.fragemt.PatientListFragment;

public class MainActivity extends BaseActivity<ActiviyMainBinding> {
    private static final String TAG = "MainActivity";
    private MainActivityAdapter mAdapter;

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
}
