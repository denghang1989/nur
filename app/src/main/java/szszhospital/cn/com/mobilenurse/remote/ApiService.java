package szszhospital.cn.com.mobilenurse.remote;


import android.content.Context;

/**
 * 2016/11/2 11
 */
public class ApiService {

    private Api mService;

    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    private ApiService() {
        mService = RetrofitHelper.getRetrofit(Api.BASE_LOGIN_URL, mContext).create(Api.class);
    }

    private static class Factory {
        private static final ApiService Instance = new ApiService();
    }

    public static ApiService Instance() {
        return Factory.Instance;
    }

    public Api getService() {
        return mService;
    }
}
