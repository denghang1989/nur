package szszhospital.cn.com.mobilenurse.view.imageplayer;

public interface OnRenderListener {

    void onStartRender(int index);

    void onFinishRender(int index);

    void onRenderError(String msg);
}
