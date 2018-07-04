package szszhospital.cn.com.mobilenurse.remote;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import szszhospital.cn.com.mobilenurse.remote.response.AuditDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DispDetailResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DrugBill;
import szszhospital.cn.com.mobilenurse.remote.response.HandlerInspectionLog;
import szszhospital.cn.com.mobilenurse.remote.response.InspectionLogDetail;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;
import szszhospital.cn.com.mobilenurse.remote.response.Order;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderSubscribe;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;
import szszhospital.cn.com.mobilenurse.remote.response.RobotDrugResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SaveAuditStatusResponse;
import szszhospital.cn.com.mobilenurse.remote.response.SchDateTimeResponse;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://172.18.0.10/trakcarelive/trak/";

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

    //获取病人列表
    @GET("web/Quality.Ajax.PatientListAjax.cls")
    Observable<Response<List<PatientInfo>>> getPatientListByLocId(@QueryMap Map<String, String> option);

    //获取医嘱信息
    @GET("web/Quality.Ajax.OrderAjax.cls")
    Observable<Response<List<Order>>> getPatientOrderList(@QueryMap Map<String, String> option);

    //获取list医嘱信息
    @GET("web/Quality.Ajax.LisOrderAjax.cls")
    Observable<Response<List<LisOrder>>> getPatientLisOrder(@Query("PatientID") String PatientID, @Query("EpisodeID") String EpisodeID);

    //获取lisdetail详细
    @GET("web/Quality.Ajax.LisOrderDetailAjax.cls")
    Observable<Response<List<LisOrderDetail>>> getLisOrderDetail(@Query("ReportDRs") String ReportDRs);

    //获取草药医嘱
    @GET("web/Quality.Ajax.HerbalOrderAjax.cls")
    Observable<Response<List<Order>>> getPatientHerbalOrder(@Query("OrderType") String OrderType, @Query("EpisodeID") String EpisodeID);

    //获取pacs医嘱
    @GET("web/Quality.Ajax.PacsOrderAjax.cls")
    Observable<Response<List<PacsOrder>>> getPatientPacsOrder(@Query("EposideId") String EpisodeID, @Query("userCode") String userCode);

    //获取病人检查病理申请单信息
    @GET("web/Quality.Ajax.InspectionAjax.cls")
    Observable<Response<PacsOrderSubscribe>> getPatientPacsSubscribe(@QueryMap Map<String, String> option);

    //获取病人检查病理申请单明细
    @GET("web/Quality.Ajax.InspectionLogAjax.cls")
    Observable<Response<List<InspectionLogDetail>>> getInspectionLogDetail(@QueryMap Map<String, String> option);

    @GET("web/Quality.Ajax.InspectionLogAjax.cls")
    Observable<Response<HandlerInspectionLog>> saveOrUpdateLog(@QueryMap Map<String, String> option);

}
