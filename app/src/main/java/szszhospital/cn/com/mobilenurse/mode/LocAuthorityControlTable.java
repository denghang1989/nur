package szszhospital.cn.com.mobilenurse.mode;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;


/**
 * 科室模块权限对照表
 */
@Table(database = AppDatabase.class)
public class LocAuthorityControlTable extends BaseModel {

    @PrimaryKey
    @Column
    public long id;

    @Column
    public int locId;

    @Column
    public int moduleId;

}
