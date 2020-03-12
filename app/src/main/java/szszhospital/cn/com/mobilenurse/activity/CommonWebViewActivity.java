package szszhospital.cn.com.mobilenurse.activity;

import android.content.Context;
import android.content.Intent;

import szszhospital.cn.com.mobilenurse.base.BaseWebViewActivity;

/**
 * @author Administrator
 */
public class CommonWebViewActivity extends BaseWebViewActivity {

    private static final String URL = "URL";
    private String mUrl;

    public static void startCommonWebViewActivity(Context context, String url) {
        Intent intent = new Intent(context, CommonWebViewActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        mUrl = getIntent().getStringExtra(URL);
    }

    @Override
    protected void initData() {
        super.initData();
        mWebView.loadUrl(mUrl);
    }
}
