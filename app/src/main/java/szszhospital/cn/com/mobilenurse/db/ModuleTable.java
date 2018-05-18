package szszhospital.cn.com.mobilenurse.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class ModuleTable extends BaseModel{

    @PrimaryKey
    @Column
    public int moduleId;

    @Column
    public String moduleDes;

    @Column
    public boolean isActivity;

    @Column
    public String className;
}
