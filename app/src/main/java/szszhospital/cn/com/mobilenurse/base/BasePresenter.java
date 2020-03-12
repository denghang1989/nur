package szszhospital.cn.com.mobilenurse.base;

/**
 * Created by zjq on 2016/7/22.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
