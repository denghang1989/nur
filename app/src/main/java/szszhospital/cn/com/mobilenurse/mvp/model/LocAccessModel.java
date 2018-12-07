package szszhospital.cn.com.mobilenurse.mvp.model;

import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

import szszhospital.cn.com.mobilenurse.App;
import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse_Table;

public class LocAccessModel implements LocAccessContract.Model {

    @Override
    public void save(List<LocAccessResponse> list) {
        List<LocAccessResponse> responseList = new Select().from(LocAccessResponse.class).where(LocAccessResponse_Table.LocId.eq(App.loginUser.UserLoc)).queryList();
        if (responseList.size()==0) {
            for (int i = 0; i < list.size(); i++) {
                LocAccessResponse response = list.get(i);
                response.save();
            }
        }
    }

    @Override
    public List<LocAccessResponse> getLocAccess(String LocId) {
        return new Select().from(LocAccessResponse.class).where(LocAccessResponse_Table.LocId.eq(App.loginUser.UserLoc)).queryList();
    }
}
