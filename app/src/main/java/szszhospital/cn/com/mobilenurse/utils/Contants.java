package szszhospital.cn.com.mobilenurse.utils;

import android.os.Environment;

import java.io.File;

public class Contants {

    public static final String PACS_PNG               = "http://172.18.0.27:8080/dicom/DicomServlet";
    public static final String PACS_DCM_DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pacs";
    public static final String PDF_DOWNLOAD_PATH      = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "pdf";

    //emr 关键字
    public static final String EMR_KEY_PATH = "EMRImagePath";

}
