package szszhospital.cn.com.mobilenurse.remote.response;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import szszhospital.cn.com.mobilenurse.entity.AppDatabase;

@Table(database = AppDatabase.class)
public class LocAccessResponse extends BaseModel {

    /**
     * Active : Y
     * Model : DrugBillUnCompletedFragment
     * ModelDesc : null
     * RowId : 1
     * Title : 未配药
     */
    @Column
    public String Active;
    @Column
    public String Model;
    @Column
    public String ModelDesc;
    @PrimaryKey
    public String RowId;
    @Column
    public String Title;
    @Column
    public String LocId;
    @Column
    public int    postion;

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
