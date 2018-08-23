package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BaseActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityImageBinding;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public class PacsImageActivity extends BaseActivity<ActivityImageBinding> {

    private static final String KEY_DATA = "data";
    private static final String KEY_PATH = "http://172.18.0.141:9876/Viewer1/Viewer.html?IDNO=";
    private PacsOrder mPacsorder;

    public static void startImageActivity(Context context, PacsOrder pacsorder) {
        Intent intent = new Intent(context, PacsImageActivity.class);
        intent.putExtra(KEY_DATA, (Parcelable) pacsorder);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void init() {
        super.init();
        mPacsorder = getIntent().getParcelableExtra(KEY_DATA);
    }

    @Override
    protected void initView() {
        super.initView();
        initWebView(mDataBinding.webView);
        initToolbar(mPacsorder);
    }

    private void initToolbar(PacsOrder pacsOrder) {
        mDataBinding.toolbar.setTitle(pacsOrder.TItemName);
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
            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        if (mPacsorder != null) {
            mDataBinding.webView.loadUrl(KEY_PATH + mPacsorder.TStudyNo);
        }
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mDataBinding.toolbar.setNavigationOnClickListener(view->finish());
    }

    @Override
    protected void onDestroy() {
        if (mDataBinding.webView != null) {
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

}
