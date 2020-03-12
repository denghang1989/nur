package szszhospital.cn.com.mobilenurse.remote.response;

public class LocAccess {

    /**
     * Active : Y
     * Model : DrugBillUnCompletedFragment
     * ModelDesc : null
     * RowId : 1
     * Title : 未配药
     */
    public String ID;
    public String Active;
    public String Model;
    public String ModelDesc;
    public String RowId;
    public String Title;

    @Override
    public String toString() {
        return "LocAccess{" +
                "Active='" + Active + '\'' +
                ", Model='" + Model + '\'' +
                ", ModelDesc=" + ModelDesc +
                ", RowId='" + RowId + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }

}
