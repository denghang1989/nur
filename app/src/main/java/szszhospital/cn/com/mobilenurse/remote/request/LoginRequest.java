package szszhospital.cn.com.mobilenurse.remote.request;

public class LoginRequest extends BaseRequest {
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
