package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 登入
 */
public class LoginRequest {
    public String className  = "Nur.Android.Common";
    public String methodName = "logon";
    public String type       = "Method";
    public String userName;
    public String password;

    @Override
    public String toString() {
        return "LoginRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
