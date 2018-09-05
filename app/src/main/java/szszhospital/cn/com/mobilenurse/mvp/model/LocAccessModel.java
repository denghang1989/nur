package szszhospital.cn.com.mobilenurse.mvp.model;

import java.util.List;

import szszhospital.cn.com.mobilenurse.mvp.contract.LocAccessContract;
import szszhospital.cn.com.mobilenurse.remote.response.LocAccessResponse;

public class LocAccessModel implements LocAccessContract.Model {

    @Override
    public void save(List<LocAccessResponse> list) {
        for (int i = 0; i < list.size(); i++) {
            LocAccessResponse response = list.get(i);
            response.save();
        }
    }
}
