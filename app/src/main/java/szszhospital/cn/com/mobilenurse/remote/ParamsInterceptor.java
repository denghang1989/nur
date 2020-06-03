package szszhospital.cn.com.mobilenurse.remote;

import android.util.Log;

import java.io.IOException;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ParamsInterceptor implements Interceptor {
    private static final String TAG = "ParamsInterceptor";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_POST = "POST";
    private static final String CACHE_USERNAME = "_system";
    private static final String CACHE_PASSWORD = "sys";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        HttpUrl.Builder urlBuilder = request.url().newBuilder();

        if (METHOD_GET.equals(request.method())) {
            // GET方法
            urlBuilder.addEncodedQueryParameter("CacheUserName", CACHE_USERNAME);
            urlBuilder.addEncodedQueryParameter("CachePassword", CACHE_PASSWORD);
            HttpUrl httpUrl = urlBuilder.build();
            
            // 打印所有get参数
            Set<String> paramKeys = httpUrl.queryParameterNames();
            for (String key : paramKeys) {
                String value = httpUrl.queryParameter(key);
                Log.d(TAG, key + " " + value);
            }
            requestBuilder.url(httpUrl);
        } else if (METHOD_POST.equals(request.method())) {
            // POST方法
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            if (request.body() instanceof FormBody) {
                FormBody formBody = (FormBody) request.body();
                for (int i = 0; i < formBody.size(); i++) {
                    bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
            }
            bodyBuilder.addEncoded("CacheUserName", CACHE_USERNAME);
            bodyBuilder.addEncoded("CachePassword", CACHE_PASSWORD);
            FormBody newBody = bodyBuilder.build();

            // 打印所有post参数
            for (int i = 0; i < newBody.size(); i++) {
            	Log.d(TAG, newBody.name(i) + " " + newBody.value(i));
            }
			 // 将最终的表单body填充到request中
            requestBuilder.post(newBody);
        }

        return chain.proceed(requestBuilder.build());
    }
}