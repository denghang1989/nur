package szszhospital.cn.com.mobilenurse.remote;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://172.18.0.116/trakcarelive/trak/web/";


    //3.账号密码登录
    @GET("Quality.Ajax.AndroidHttpResponse.cls")
    Observable<LoginResponse> login(@QueryMap Map<String, String> option);
}
