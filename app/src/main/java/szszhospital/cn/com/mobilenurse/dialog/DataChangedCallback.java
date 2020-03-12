package szszhospital.cn.com.mobilenurse.dialog;

public interface DataChangedCallback<T> {
    void onMove(T data);
    void onSwiped(T data);
}
