package szszhospital.cn.com.mobilenurse;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import szszhospital.cn.com.mobilenurse.control.LocAccess;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.model.LoginUser;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

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

    public static LocAccess access;

    public static void setAccess(LocAccess access) {
        App.access = access;
    }

    public static PatientInfo patientInfo;

    public static void setPatientInfo(PatientInfo patientInfo) {
        App.patientInfo = patientInfo;
    }

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        ApiService.init(this);
        mContext = getApplicationContext();
    }
}
