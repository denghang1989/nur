package szszhospital.cn.com.mobilenurse.remote.response;

import java.util.List;

public class RobotDrugResponse {


    /**
     * RetCode : 0
     * RetDesc : 配药成功
     * data : [{"AuditItmDr":"7||1","ConFirmFlag":"A"}]
     */

    public String         RetCode;
    public String         RetDesc;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * AuditItmDr : 7||1
         * ConFirmFlag : A
         */

        public String AuditItmDr;
        public String ConFirmFlag;
    }
}
