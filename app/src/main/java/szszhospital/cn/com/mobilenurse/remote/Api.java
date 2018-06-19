package szszhospital.cn.com.mobilenurse.remote;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import szszhospital.cn.com.mobilenurse.remote.response.AuditDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;
import szszhospital.cn.com.mobilenurse.remote.response.RobotDrugResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SaveAuditStatusResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SchDateTimeResponse;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://172.18.0.12/trakcarelive/trak/";

    //3.账号密码登录
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<LoginResponse>> login(@QueryMap Map<String, String> option);

    //每次登陆清除
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<SchDateTimeResponse>> clearCacheLogin(@QueryMap Map<String, String> option);

    //获取整个发药单信息
    @GET("web/Quality.Ajax.DispListAjax.cls")
    Observable<Response<List<DrugBill>>> getDrugBillList(@QueryMap Map<String, String> option);

    //获取发药单明细列表
    @GET("web/Quality.Ajax.DispDetailListAjax.cls")
    Observable<Response<List<DispDetailResponse>>> getDispDetailList(@QueryMap Map<String, String> option);

    //单个药品配药
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<List<AuditDetailResponse>>> updateDrugState(@QueryMap Map<String, String> option);

    //更新发药单状态
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<List<SaveAuditStatusResponse>>> saveAuditStatus(@QueryMap Map<String, String> option);

    //获取登入科室模块
    @GET("web/Quality.Ajax.AndroidAccessAjax.cls")
    Observable<Response<List<LocAccessResponse>>> getLocAccess(@QueryMap Map<String, String> option);

    //摆药机的药品明细
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<RobotDrugResponse>> updateRobotDrugState(@QueryMap Map<String, String> option);
}
