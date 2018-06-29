package szszhospital.cn.com.mobilenurse.remote.response;

import java.util.List;

/**
 * pacs 预约信息
 */
public class PacsOrderSubscribe {

    /**
     * rows : [{"AdmBed":"0006","Hosno":"223341","PatAge":"31岁","PatLoc":"CSBQ-测试病区","PatName":"小赵","PatNo":"03435887","PatSex":"男","PrintFlag":"","ReqData":"2018-06-27","ReqTime":"16:52","ReqUser":"张淑琼","ReserInfo":" --","Status":"","StatusCode":"核实","Type":"检查","arExLocDesc":"FSYXK-放射影像科","arRepID":"294572","arReqNo":"APPI2018062701233","arcListData":"DR【鼻骨侧位*(1)、鼻咽侧位*(1)、蝶鞍侧位*(1)】","repEmgFlag":"否"}]
     * total : 1
     */

    public int total;
    public List<OrderSubscribe> rows;

    public static class OrderSubscribe {
        /**
         * AdmBed : 0006
         * Hosno : 223341
         * PatAge : 31岁
         * PatLoc : CSBQ-测试病区
         * PatName : 小赵
         * PatNo : 03435887
         * PatSex : 男
         * PrintFlag :
         * ReqData : 2018-06-27
         * ReqTime : 16:52
         * ReqUser : 张淑琼
         * ReserInfo :  --
         * Status :
         * StatusCode : 核实
         * Type : 检查
         * arExLocDesc : FSYXK-放射影像科
         * arRepID : 294572
         * arReqNo : APPI2018062701233
         * arcListData : DR【鼻骨侧位*(1)、鼻咽侧位*(1)、蝶鞍侧位*(1)】
         * repEmgFlag : 否
         */

        public String AdmBed;
        public String Hosno;
        public String PatAge;
        public String PatLoc;
        public String PatName;
        public String PatNo;
        public String PatSex;
        public String PrintFlag;
        public String ReqData;
        public String ReqTime;
        public String ReqUser;
        public String ReserInfo;
        public String Status;
        public String StatusCode;
        public String Type;
        public String arExLocDesc;
        public String arRepID;
        public String arReqNo;
        public String arcListData;
        public String repEmgFlag;
    }
}
