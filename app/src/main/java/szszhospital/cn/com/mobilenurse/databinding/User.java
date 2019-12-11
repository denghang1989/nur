package szszhospital.cn.com.mobilenurse.databinding;

import android.databinding.Bindable;


public class User {

    private String name;
    private String password;
    private boolean isLogin;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
