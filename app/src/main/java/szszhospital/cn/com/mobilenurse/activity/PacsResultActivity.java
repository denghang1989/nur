package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsResultBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PacsResult;
import szszhospital.cn.com.mobilenurse.utils.GsonUtil;

public class PacsResultActivity extends BasePresentActivity<ActivityPacsResultBinding, PacsResultPresenter> implements PacsResultContract.View {

    private static final String KEY_DATA = "data";
    private static final String TAG      = "PacsResultActivity";
    private PacsOrder mPacsOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pacs_result;
    }

    public static void startPacsResultActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsResultActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mPacsOrder = getIntent().getParcelableExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
        initWebView(mDataBinding.webView);
        initToolbar(mPacsOrder);
    }

    private void initToolbar(PacsOrder pacsOrder) {
        mDataBinding.toolbar.setTitle(pacsOrder.TItemName);
    }

    private void initWebView(BridgeWebView webView) {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webView.setDefaultHandler(new DefaultHandler());
        webView.loadUrl("file:///android_asset/pacs/index.html");
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getPacsResult(mPacsOrder.TOEOrderDr);
        Map<String, String> map = new HashMap<>();
        map.put("recordNo", App.patientInfo.MedicareNo);
        map.put("name", App.patientInfo.PAPMIName);
        map.put("sex", App.patientInfo.Sex);
        map.put("age", App.patientInfo.Age);
        map.put("patientNo", App.patientInfo.PatientID);
        map.put("bedNo", App.patientInfo.DisBed);
        String userLocDesc = App.loginUser.UserLocDesc;
        String[] split = userLocDesc.split("-");
        map.put("loc", split[0]);
        mDataBinding.webView.callHandler("sendPatientInfo", GsonUtil.GsonString(map), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.d(TAG, "onCallBack: ");
            }
        });
    }

    @Override
    protected void onDestroy() {
        if( mDataBinding.webView!=null) {
            ViewParent parent = mDataBinding.webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mDataBinding.webView);
            }

            mDataBinding.webView.stopLoading();
            mDataBinding.webView.getSettings().setJavaScriptEnabled(false);
            mDataBinding.webView.clearHistory();
            mDataBinding.webView.clearView();
            mDataBinding.webView.removeAllViews();
            mDataBinding.webView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected PacsResultPresenter initPresenter() {
        return new PacsResultPresenter();
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
    public void showPacsResult(PacsResult pacsResult) {
        Map<String, String> map = new HashMap<>();
        map.put("method", pacsResult.rows.get(0).strOrderName);
        map.put("observe", pacsResult.rows.get(0).ExamDesc);
        map.put("opinion", pacsResult.rows.get(0).strResult);
        mDataBinding.webView.callHandler("sendReport", GsonUtil.GsonString(map), new CallBackFunction() {
            @Override
            public void onCallBack(String data) {
                Log.d(TAG, "onCallBack: ");
            }
        });
    }
}
