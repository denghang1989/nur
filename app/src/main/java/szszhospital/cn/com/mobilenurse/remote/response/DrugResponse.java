package szszhospital.cn.com.mobilenurse.remote.response;

import java.util.List;

public class DrugResponse {

    /**
     * rows : [{"wardDesc":"呼吸病科护士站","bedCode":"11床床","patName":"杨国强","drugName":"{市}复方甲氧那明胶囊[合资](60粒)","dispQty":"60 粒","doseQty":"2.000粒","regNo":"01498233","inciId":"4163","drugCode":"12302131","dispAuditId":"10||26","dispAuditStatus":"P","dispAuditStatusDesc":"发药  pharmacy  2018-05-18 15:12:14","oeoreId":"14511587||239","prefDispStatus":"N"},{"wardDesc":"呼吸病科护士站","bedCode":"30床床","patName":"刘铁金","drugName":"{市}复方甲氧那明胶囊[合资](60粒)","dispQty":"60 粒","doseQty":"2.000粒","regNo":"00087590","inciId":"4163","drugCode":"12302131","dispAuditId":"10||27","dispAuditStatus":"N","dispAuditStatusDesc":"未发药    ","oeoreId":"14575933||26","prefDispStatus":"N"},{"wardDesc":"呼吸病科护士站","bedCode":"30床床","patName":"刘铁金","drugName":"{市}盐酸氨溴索片(30mg*20片)","dispQty":"20 片","doseQty":"30.000mg","regNo":"00087590","inciId":"18512","drugCode":"12301228","dispAuditId":"10||28","dispAuditStatus":"N","dispAuditStatusDesc":"未发药    ","oeoreId":"14575933||25","prefDispStatus":"N"}]
     * results : 3
     */

    public int results;
    public List<Drug> rows;
}
