package szszhospital.cn.com.mobilenurse.fragemt;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseFragment;
import szszhospital.cn.com.mobilenurse.databinding.FragmentVitalSignsBinding;
import szszhospital.cn.com.mobilenurse.event.SelectPatientEvent;
import szszhospital.cn.com.mobilenurse.remote.ApiService;
import szszhospital.cn.com.mobilenurse.remote.RxUtil;
import szszhospital.cn.com.mobilenurse.remote.response.VitalSignsPath;

/**
 * 生命体征
 */
public class VitalSignsFragment extends BaseFragment<FragmentVitalSignsBinding> {
    private static final String TAG = "VitalSignsFragment";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_vital_signs;
    }

    public static VitalSignsFragment newInstance() {
        return new VitalSignsFragment();
    }

    @Override
    protected void initView() {
        super.initView();
        initWebView(mDataBinding.webView);
    }

    private void initWebView(WebView webView) {
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

        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        webView.setWebChromeClient(new WebChromeClient() {

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    mDataBinding.progressBar.setVisibility(View.VISIBLE);
                    mDataBinding.progressBar.setProgress(newProgress);
                } else if (newProgress == 100) {
                    mDataBinding.progressBar.setVisibility(View.GONE);
                }
            }
        });

        //设置WebViewClient类
        webView.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG, "onPageStarted: " + "开始加载");
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "onPageFinished: " + "结束加载");
            }
        });
    }


    @Override
    protected void initData() {
        super.initData();
        if (App.patientInfo != null) {
            ApiService.Instance().getService().getVitalSignsPath(App.patientInfo.EpisodeID, App.patientInfo.PatientID)
                    .compose(RxUtil.httpHandleResponse())
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribe(new Observer<VitalSignsPath>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(VitalSignsPath vitalSignsPath) {
                            if (vitalSignsPath != null) {
                                mDataBinding.webView.loadUrl(vitalSignsPath.path);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDataBinding.webView.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mDataBinding.webView.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDataBinding.webView.destroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void selectedPatient(SelectPatientEvent event) {
        if (isSupportVisible()) {
            initData();
        }
    }

}
