package szszhospital.cn.com.mobilenurse.remote.response;

import java.util.List;

public class PacsResult {

    /**
     * total : 1
     * rows : [{"OEItemRowID":"15631746||100","ExamDesc":"腹主动脉内因腹腔胀气部分显示不清,能显示处未见明显异常。肾动脉由于腹腔气体干扰,二维图像显示欠清。左肾动脉频谱:Vs:55.1cm/s,Vd:6.8cm/s,RI:0.88。右肾动脉频谱:Vs:52.4cm/s,Vd:6.4cm/s,RI:0.88。","strResult":"双肾动脉血流阻力指数增高,请结合临床。","strOrderName":"双肾动脉及腹主动脉彩超"}]
     */

    public int total;
    public List<RowsBean> rows;

    public static class RowsBean {
        /**
         * OEItemRowID : 15631746||100
         * ExamDesc : 腹主动脉内因腹腔胀气部分显示不清,能显示处未见明显异常。肾动脉由于腹腔气体干扰,二维图像显示欠清。左肾动脉频谱:Vs:55.1cm/s,Vd:6.8cm/s,RI:0.88。右肾动脉频谱:Vs:52.4cm/s,Vd:6.4cm/s,RI:0.88。
         * strResult : 双肾动脉血流阻力指数增高,请结合临床。
         * strOrderName : 双肾动脉及腹主动脉彩超
         */

        public String OEItemRowID;
        public String ExamDesc;
        public String strResult;
        public String strOrderName;
    }
}
