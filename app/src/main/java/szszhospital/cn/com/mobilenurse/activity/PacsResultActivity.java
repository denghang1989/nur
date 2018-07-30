package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import szszhospital.cn.com.mobilenurse.R;
import szszhospital.cn.com.mobilenurse.base.BasePresentActivity;
import szszhospital.cn.com.mobilenurse.databinding.ActivityPacsResultBinding;
import szszhospital.cn.com.mobilenurse.mvp.contract.PacsResultContract;
import szszhospital.cn.com.mobilenurse.mvp.presenter.PacsResultPresenter;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PacsResult;

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
        mPresenter.getPacsResult(mPacsOrder.TOEOrderDr);
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
        Log.d(TAG, "showPacsResult: " + pacsResult.toString());
    }
}
