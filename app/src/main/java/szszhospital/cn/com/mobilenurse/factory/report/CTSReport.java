package szszhospital.cn.com.mobilenurse.factory.report;

import android.webkit.WebView;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;


/**
 * CTS-CT室
 */
public class CTSReport implements WebViewReportHandler {


    @Override
    public void openReport(PacsOrder pacsOrder, WebView view) {

    }

    @Override
    public String openImage(PacsOrder pacsOrder) {
        return "01";
    }
}
