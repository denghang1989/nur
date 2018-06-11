package szszhospital.cn.com.mobilenurse.remote.response;

public class LocAccessResponse {

    /**
     * Active : Y
     * Model : DrugBillUnCompletedFragment
     * ModelDesc : null
     * RowId : 1
     * Title : 未配药
     */

    public String Active;
    public String Model;
    public Object ModelDesc;
    public String RowId;
    public String Title;

    @Override
    public String toString() {
        return "LocAccessResponse{" +
                "Active='" + Active + '\'' +
                ", Model='" + Model + '\'' +
                ", ModelDesc=" + ModelDesc +
                ", RowId='" + RowId + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }
}
