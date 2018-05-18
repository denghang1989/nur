package szszhospital.cn.com.mobilenurse.remote;


import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.observable.ObservableError;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RxUtil {
    private static final String TAG = "RxUtil";


    /**
     * 判断返回的list是否为空
     */
    public static Function<List<?>, Boolean> listBooleanFunction = new Function<List<?>, Boolean>() {
        @Override
        public Boolean apply(List<?> list) throws Exception {
            return list != null && list.size() > 0;
        }
    };

    public static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                emitter.onNext(t);
                emitter.onComplete();
            }
        });
    }

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<Response<T>, T> httpHandleResponse() {
        return new ObservableTransformer<Response<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Response<T>> upstream) {
                return upstream.flatMap(new Function<Response<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(Response<T> tResponse) throws Exception {
                        if (tResponse.code() == 200) {
                            String cookie = tResponse.headers().get("Set-Cookie");
                            Log.d(TAG, "apply: " + cookie);
                            RetrofitHelper.cookie = cookie;
                            return createObservable(tResponse.body());
                        } else {
                            return ObservableError.error(new ApiException(tResponse.message()));
                        }
                    }
                });
            }
        };
    }

}
