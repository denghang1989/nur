package szszhospital.cn.com.mobilenurse.factory.report;

import android.webkit.WebView;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;

public interface WebViewReportHandler {

    void openReport(PacsOrder pacsOrder, WebView view);

    String openImage(PacsOrder pacsOrder);

}
