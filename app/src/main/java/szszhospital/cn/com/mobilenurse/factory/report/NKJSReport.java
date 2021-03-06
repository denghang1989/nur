package szszhospital.cn.com.mobilenurse.factory.report;

import android.webkit.WebView;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.utils.GenericCode;


/**
 * NKJS-内窥镜室
 */
public class NKJSReport implements WebViewReportHandler {

    public static final int LENGTH = 8;

    public String getReportUrl(PacsOrder pacsOrder) {
        String url = "";
        String data = pacsOrder.TReplocpath;
        String[] split = data.split("\\^");
        String reportFullFil = split[0];
        if (!reportFullFil.contains(".exe") && reportFullFil.startsWith("http")) {
            if (pacsOrder.TRegNo.trim().length() < LENGTH) {
                url = reportFullFil + GenericCode.autoGenericCode(pacsOrder.TRegNo, LENGTH);
            } else {
                url = reportFullFil + pacsOrder.TRegNo;
            }
        }
        return url;
    }

    @Override
    public void openReport(PacsOrder pacsOrder, WebView view) {
        String url = getReportUrl(pacsOrder);
        view.loadUrl(url);
    }

    @Override
    public String openImage(PacsOrder pacsOrder) {
        return "03";
    }
}
