package szszhospital.cn.com.mobilenurse.remote.response;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class DcmName extends BaseModel {

    @PrimaryKey
    @Column
    public String KEY;

    @Column
    public String IMAGEPATH;

    @Column
    public String IMAGENAME;

    @Override
    public String toString() {
        return "DcmName{" +
                "IMAGEPATH='" + IMAGEPATH + '\'' +
                ", IMAGENAME='" + IMAGENAME + '\'' +
                '}';
    }
}
