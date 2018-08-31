package szszhospital.cn.com.mobilenurse.remote.response;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import szszhospital.cn.com.mobilenurse.entity.AppDatabase;

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
