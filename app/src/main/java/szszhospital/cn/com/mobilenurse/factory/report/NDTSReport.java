package szszhospital.cn.com.mobilenurse.factory.report;

import android.webkit.WebView;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;


/**
 * NDTS-脑电图室
 */
public class NDTSReport implements WebViewReportHandler {

    @Override
    public void openReport(PacsOrder pacsOrder, WebView view) {

    }

    @Override
    public String openImage(PacsOrder pacsOrder) {
        return "";
    }
}
