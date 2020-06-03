package szszhospital.cn.com.mobilenurse.remote;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 2016/9/21 10:03
 */
public class RetrofitHelper {

    private static OkHttpClient okHttpClient = null;

    public static String cookie = "";

    public static Retrofit getRetrofit(String baseUrl, Context context) {
        initOkHttp(context);
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient initOkHttp(final Context context) {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(new ParamsInterceptor());
            builder.addInterceptor(loggingInterceptor);
            File cacheFile = new File(context.getCacheDir(), "http");
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            builder.cache(cache);
            builder.cookieJar(new CookieJar() {
                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            });
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
