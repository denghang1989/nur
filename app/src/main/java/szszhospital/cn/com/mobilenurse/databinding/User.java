package szszhospital.cn.com.mobilenurse.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import szszhospital.cn.com.mobilenurse.BR;

public class User extends BaseObservable {

    private String name;
    private String password;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
