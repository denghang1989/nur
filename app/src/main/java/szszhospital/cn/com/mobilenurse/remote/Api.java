package szszhospital.cn.com.mobilenurse.remote;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://172.18.0.116/trakcarelive/trak/";

    //3.账号密码登录
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<LoginResponse>> login(@QueryMap Map<String, String> option);

    //每次登陆清除
    @GET("web/Quality.Ajax.AndroidHttpResponse.cls")
    Observable<Response<String>> clearCacheLogin(@QueryMap Map<String, String> option);

}
