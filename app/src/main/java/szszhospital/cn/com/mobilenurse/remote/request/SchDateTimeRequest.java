package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 登入以后删除cache零时数据
 */
public class SchDateTimeRequest {
    public String className  = "Nur.Android.Common";
    public String methodName = "DelSchDateTimeJson";
    public String type       = "Method";
    public String user;

    @Override
    public String toString() {
        return "SchDateTimeRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
