package szszhospital.cn.com.mobilenurse.remote.response;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

public class Order implements  Serializable, MultiItemEntity {

    public static final int V_ORDER = 0;
    public static final int D_ORDER = 1;

    /**
     * ArcimDesc : 血常规
     * Doctor : 郭良
     * DoseQty :
     * DoseUnit :
     * Dura :
     * Instr :
     * LabEpisodeNo : 7727061
     * OEItemID : 15194639||7
     * OEORIPhQty : 1
     * OEORIQty :
     * OrdAction :
     * OrdBilled : TB
     * OrdCreateDate : 2018-05-07
     * OrdCreateTime : 16:49
     * OrdDepProcNotes :
     * OrdLabSpec : 全血(EDTA)
     * OrdSkinTest : N
     * OrdSkinTestResult :
     * OrdStartDate : 2018-05-07
     * OrdStartTime : 16:49
     * OrdStatus : 执行
     * OrdXDate :
     * OrdXTime :
     * PHFreq :
     * PackUOMDesc : 项
     * PatientID : 3378288
     * PrescNo :
     * Priority : 临时医嘱
     * QtyPackUOM :
     * RecipeInfo :
     * Reflag :
     * SeqNo : 1
     * dstatus :
     */

    public String ArcimDesc;
    public String Doctor;
    public String DoseQty;
    public String DoseUnit;
    public String Dura;
    public String Instr;
    public String LabEpisodeNo;
    public String OEItemID;
    public String OEORIPhQty;
    public String OEORIQty;
    public String OrdAction;
    public String OrdBilled;
    public String OrdCreateDate;
    public String OrdCreateTime;
    public String OrdDepProcNotes;
    public String OrdLabSpec;
    public String OrdSkinTest;
    public String OrdSkinTestResult;
    public String OrdStartDate;
    public String OrdStartTime;
    public String OrdStatus;
    public String OrdXDate;
    public String OrdXTime;
    public String PHFreq;
    public String PackUOMDesc;
    public String PatientID;
    public String PrescNo;
    public String Priority;
    public String QtyPackUOM;
    public String RecipeInfo;
    public String Reflag;
    public String SeqNo;
    public String dstatus;
    /**
     * OrdStatusCode : V
     * OrdStopDate :
     * OrdStopTime :
     */

    public String OrdStatusCode;
    public String OrdStopDate;
    public String OrdStopTime;
    /**
     * ArcimId : 7129||1
     * DoseQty : 1
     * OEORIPhQty : 1
     * OrdStopDoctor :
     * PatientID : 113253
     * SeqNo : 1
     */

    public String ArcimId;
    public String OrdStopDoctor;

    @Override
    public int getItemType() {
        int result = 0;
        switch (OrdStatusCode) {
            case "":
            case "V":
                result = 0;
                break;
            case "D":
                result = 1;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

}
