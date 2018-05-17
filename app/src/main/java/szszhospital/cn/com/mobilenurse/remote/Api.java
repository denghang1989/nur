package szszhospital.cn.com.mobilenurse.remote;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import szszhospital.cn.com.mobilenurse.remote.request.LoginRequest;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

/**
 * 2016/11/2 11
 */
public interface Api {
    String BASE_LOGIN_URL = "http://172.18.0.116/trakcarelive/trak/web/";


    //3.账号密码登录
    @POST("csp/dhc.nurse.androidpda.common.getdata.csp")
    Observable<Response<LoginResponse>> login(@Body LoginRequest request);
}
