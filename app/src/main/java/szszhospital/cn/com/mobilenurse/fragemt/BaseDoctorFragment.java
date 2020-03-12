package szszhospital.cn.com.mobilenurse.fragemt;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.databinding.ViewDataBinding;
import szszhospital.cn.com.mobilenurse.base.BasePresenter;
import szszhospital.cn.com.mobilenurse.base.BasePresenterFragment;
import szszhospital.cn.com.mobilenurse.base.BaseView;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;

public abstract class BaseDoctorFragment<T extends ViewDataBinding, P extends BasePresenter> extends BasePresenterFragment<T, P> implements BaseView {

    @Override
    protected void init() {
        super.init();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectedPatient(SelectPatientEvent event) {
        initData();
    }


}
