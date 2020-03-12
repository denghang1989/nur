package szszhospital.cn.com.mobilenurse.entity;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class PacsImagePath extends BaseModel {

    /**
     * FEXAMKIND : 01
     * SERIESUID : 1.2.840.113564.172183264.2017122216511478183
     * IMAGEPATH : /image_pacs/20171222/A1033325/1.2.840.113564.172183264.2017122216511478183/
     * MODALITY : CR
     * STUDYID : A1033325
     */

    @Column
    public String FEXAMKIND;
    @Column
    public String SERIESUID;
    @PrimaryKey
    @Column
    public String IMAGEPATH;
    @Column
    public String MODALITY;
    @Column
    public String STUDYID;
    @Column
    public String thumbnailPath;
    @Column
    public String name;

    @Override
    public String toString() {
        return "PacsImagePath{" +
                "FEXAMKIND='" + FEXAMKIND + '\'' +
                ", SERIESUID='" + SERIESUID + '\'' +
                ", IMAGEPATH='" + IMAGEPATH + '\'' +
                ", MODALITY='" + MODALITY + '\'' +
                ", STUDYID='" + STUDYID + '\'' +
                '}';
    }
}
