package szszhospital.cn.com.mobilenurse.mvp.model;

import java.util.List;

import szszhospital.cn.com.mobilenurse.mvp.contract.LisOrderContract;
import szszhospital.cn.com.mobilenurse.remote.response.LisOrder;

public class LisOrderModel implements LisOrderContract.Model {

    @Override
    public void save(List<LisOrder> list) {
        for (LisOrder lisOrder : list) {
            lisOrder.save();
        }
    }
}
