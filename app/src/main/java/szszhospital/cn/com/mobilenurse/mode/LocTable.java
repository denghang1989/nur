package szszhospital.cn.com.mobilenurse.mode;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class LocTable extends BaseModel {

    @PrimaryKey
    @Column
    public String LocID;

    @Column
    public String LocDesc;

    @Column
    public String GroupID;

    @Column
    public String WardID;
}
