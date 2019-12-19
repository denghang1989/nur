package szszhospital.cn.com.mobilenurse.remote;


import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import szszhospital.cn.com.mobilenurse.remote.response.BaseResponse;
import szszhospital.cn.com.mobilenurse.remote.response.DrugAllergy;
import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;
import szszhospital.cn.com.mobilenurse.remote.response.EMRImageInfo;
import szszhospital.cn.com.mobilenurse.remote.response.LisChartData;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrderDetail;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccess;
import szszhospital.cn.com.mobilenurse.remote.response.LocInfo;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;
import szszhospital.cn.com.mobilenurse.remote.response.Order;
import szszhospital.cn.com.mobilenurse.remote.response.OrderExecuteInfo;
import szszhospital.cn.com.mobilenurse.remote.response.PacsImagePath;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrder;
import szszhospital.cn.com.mobilenurse.remote.response.PacsOrderItem;
import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;
import szszhospital.cn.com.mobilenurse.remote.response.UpdateApp;
import szszhospital.cn.com.mobilenurse.remote.response.VitalSignsPath;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://192.168.199.49/dthealth/web/";

    //3.账号密码登录
    @POST("web/Quality.Ajax.LoginAjax.cls")
    Observable<Response<BaseResponse<LoginResponse>>> login(@Query("userName") String userName, @Query("password") String password);

    //获取全部的登入科室
    @POST("web/Quality.Ajax.LoginLocAjax.cls")
    Observable<Response<BaseResponse<List<LocInfo>>>> getLoginLoc(@Query("userId") String userId);

    //获取登入科室模块
    @GET("web/Quality.Ajax.AndroidAccessAjax.cls")
    Observable<Response<List<LocAccess>>> getLocAccess(@Query("LocId") String userId);

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

    //获取pacs医嘱
    @GET("web/Quality.Ajax.PacsOrderAjax.cls")
    Observable<Response<List<PacsOrderItem>>> getEposidePacsOrder(@Query("EposideId") String EpisodeID);

    //检查apk 更新
    @GET("web/Quality.Ajax.UpdateAppAjax.cls")
    Observable<Response<UpdateApp>> getUpdateAppInfo();

    //医嘱执行记录
    @GET("web/Quality.Ajax.OrderExecuteAjax.cls")
    Observable<Response<List<OrderExecuteInfo>>> getOrderExecuteList(@Query("OrderId") String orderId);

    //获取病人图片的列表
    @GET("web/Quality.Ajax.EMREposideListAjax.cls")
    Observable<Response<List<EMREposideInfo>>> getEMREposideList(@Query("EpisodeID") String EpisodeID);

    //获取图片的ftp地址  Quality.Ajax.EMRImageListAjax
    @GET("web/Quality.Ajax.EMRImageListAjax.cls")
    Observable<Response<List<EMRImageInfo>>> getEMRImageList(@Query("EpisodeID") String EpisodeID, @Query("InternalID") String InternalID);

    //获取护理病历的地址
    @GET("web/Quality.Ajax.VitalSignsAjax.cls")
    Observable<Response<VitalSignsPath>> getVitalSignsPath(@Query("EpisodeID") String EpisodeID, @Query("PatientID") String PatientID);

    //获取图片列表
    @GET("web/Quality.Ajax.PacsImagePathAjax.cls")
    Observable<Response<List<PacsImagePath>>> getPacsImageFtpPath(@Query("studyId") String studyId, @Query("type") String type);

    //获取lis历次结果列表
    @GET("web/Quality.Ajax.LisChartAjax.cls")
    Observable<Response<List<LisChartData>>> getLisChartData(@Query("ReportDR") String ReportResultDR, @Query("TestCodeDR") String TestCodeDR);

    //获取药敏结果   http://172.18.0.3:57772/trakcarelive/trak/web/Quality.Ajax.LisMResultAjax.cls?ReportDRs=24319627
    @GET("web/Quality.Ajax.LisMResultAjax.cls")
    Observable<Response<List<DrugAllergy>>> getLisDrugAllergyData(@Query("ReportDRs") String ReportDRs);

    //通过登记号获取就诊列表
    @GET("web/Quality.Ajax.PatientNoListAjax.cls")
    Observable<Response<List<PatientInfo>>> getPatientListByNo(@Query("PapmiNo") String PapmiNo);


}
