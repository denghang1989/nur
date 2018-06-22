package szszhospital.cn.com.mobilenurse;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.model.LoginUser;

public class App extends MultiDexApplication {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
        });
    }

    public static LoginUser loginUser = new LoginUser();

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        FlowManager.init(this);
        ApiService.init(this);
    }
}
