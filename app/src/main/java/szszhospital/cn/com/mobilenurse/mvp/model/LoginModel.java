package szszhospital.cn.com.mobilenurse.mvp.model;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.db.LocTable;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

public class LoginModel implements LoginContract.Model {

    @Override
    public void save(LoginResponse loginResponse) {
        App.loginUser.UserDR = loginResponse.UserID;
        App.loginUser.UserName = loginResponse.UserName;
        for (int i = 0; i < loginResponse.Locs.size(); i++) {
            LocTable table = loginResponse.Locs.get(i);
            table.save();
        }
    }
}
