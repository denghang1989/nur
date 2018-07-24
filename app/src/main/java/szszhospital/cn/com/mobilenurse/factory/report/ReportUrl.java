package szszhospital.cn.com.mobilenurse.factory.report;

import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PascClinicSetting;

public interface ReportUrl {

    String getReportUrl(PascClinicSetting pascClinicSetting, PacsOrder pacsOrder);
}
