package szszhospital.cn.com.mobilenurse.mode;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * 模块
 */
@Table(database = AppDatabase.class)
public class ModuleTable extends BaseModel {

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
