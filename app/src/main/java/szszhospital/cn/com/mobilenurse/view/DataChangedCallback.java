package szszhospital.cn.com.mobilenurse.view;

public interface DataChangedCallback<T> {
    void onMove(T data);
    void onSwiped(T data);
}
