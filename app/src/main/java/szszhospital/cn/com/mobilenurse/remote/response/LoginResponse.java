package szszhospital.cn.com.mobilenurse.remote.response;

import java.util.List;

import szszhospital.cn.com.mobilenurse.mode.LocTable;

public class LoginResponse {
    public String    ErrorInfo;
    public String    UserID;
    public String    UserName;
    public List<LocTable> Locs;
}
