package szszhospital.cn.com.mobilenurse;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.Utils;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.io.File;

import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.model.LoginUser;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;
import szszhospital.cn.com.mobilenurse.utils.AppUtil;
import szszhospital.cn.com.mobilenurse.utils.AsynHandlerThread;
import szszhospital.cn.com.mobilenurse.utils.Contants;

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

    private static Handler mAsynHandler;

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
        FlowManager.init(this);
        mContext = getApplicationContext();
        HandlerThread handlerThread = new AsynHandlerThread();
        handlerThread.start();
        mAsynHandler = new Handler(handlerThread.getLooper());
        //判断一下存储空间 如果超过20G 删除数据
        mAsynHandler.post(() -> {
            initPacsFile();
            initPdfFile();
        });
    }

    private void initPdfFile() {
        File pdf = new File(Contants.PDF_DOWNLOAD_PATH);
        if (!pdf.exists()) {
            pdf.mkdir();
        }
    }

    private void initPacsFile() {
        File pacs = new File(Contants.PACS_DCM_DOWNLOAD_PATH);
        if (pacs.exists()) {
            long length = FileUtils.getDirLength(Contants.PACS_DCM_DOWNLOAD_PATH);
            if (length > AppUtil.GB * 20) {
                FileUtils.deleteAllInDir(Contants.PACS_DCM_DOWNLOAD_PATH);
            }
        } else {
            pacs.mkdir();
        }
    }

    public static Handler getAsynHandler() {
        return mAsynHandler;
    }

}
