package szszhospital.cn.com.mobilenurse.factory.report;

import android.webkit.WebView;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;


/**
 * FSYXK-放射影像科
 */
public class FSYXKReport implements WebViewReportHandler {

    @Override
    public void openReport(PacsOrder pacsOrder, WebView view) {

    }

    @Override
    public String openImage(PacsOrder pacsOrder) {
        return "01";
    }
}
