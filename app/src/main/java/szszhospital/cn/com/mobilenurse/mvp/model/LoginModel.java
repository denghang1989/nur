package szszhospital.cn.com.mobilenurse.mvp.model;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.mvp.contract.LoginContract;
import szszhospital.cn.com.mobilenurse.remote.response.LoginResponse;

/**
 * @author admin
 */
public class LoginModel implements LoginContract.Model {

    @Override
    public void save(LoginResponse loginResponse) {
        App.loginUser.UserDR = loginResponse.getId();
        App.loginUser.UserName = loginResponse.getName();
        App.loginUser.UserLoc = loginResponse.getLocId();
    }
}
