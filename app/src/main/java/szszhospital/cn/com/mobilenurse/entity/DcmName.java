package szszhospital.cn.com.mobilenurse.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class DcmName extends BaseModel {

    @Column
    public String IMAGEPATH;

    @PrimaryKey
    public String IMAGENAME;

    @Column
    public long size;

    @Override
    public String toString() {
        return "DcmName{" +
                "IMAGEPATH='" + IMAGEPATH + '\'' +
                ", IMAGENAME='" + IMAGENAME + '\'' +
                '}';
    }
}
