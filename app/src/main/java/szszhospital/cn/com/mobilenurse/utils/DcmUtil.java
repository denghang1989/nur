package szszhospital.cn.com.mobilenurse.utils;

import android.graphics.Bitmap;
import android.util.Log;

import com.blankj.utilcode.util.ImageUtils;

import org.dcm4che3.android.Raster;
import org.dcm4che3.android.RasterUtil;
import org.dcm4che3.android.imageio.dicom.DicomImageReader;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;

import java.io.File;

public class DcmUtil {
    /**
     * 读取文件数据
     */
    public static File readFile(String filePath) {
        File changeFile = null;
        if (filePath.endsWith(".dcm")) {
            String desc = filePath.replace(".dcm",".png");
            changeFile = new File(desc);
            if (changeFile.isFile() && changeFile.exists()) {
                return changeFile;
            }
        }
        try {
            DicomImageReader dr = new DicomImageReader();
            File file = new File(filePath);
            //dcm文件输入流
            DicomInputStream dcmInputStream = new DicomInputStream(file);
            //属性对象
            Attributes attrs = dcmInputStream.readDataset(-1, -1);
            //输出所有属性信息
            //Log.e("TAG", "输出所有属性信息1:" + attrs);
            //获取行
            int row = attrs.getInt(Tag.Rows, 1);
            //获取列
            int columns = attrs.getInt(Tag.Columns, 1);
            //窗宽窗位
            float win_center = attrs.getFloat(Tag.WindowCenter, 1);
            float win_width = attrs.getFloat(Tag.WindowWidth, 1);

            dr.open(file);
            Raster raster = dr.applyWindowCenter(0, (int) win_width, (int) win_center);
            Bitmap bitmap = RasterUtil.gray8ToBitmap(columns, row, raster.getByteData());
            if (filePath.endsWith(".dcm")) {
                String desc = filePath.replace(".dcm",".png");
                changeFile = new File(desc);
                boolean save = ImageUtils.save(bitmap, desc, Bitmap.CompressFormat.PNG, true);
                if (save) {
                    return changeFile;
                }
            }
        } catch (Exception e) {
            Log.e("TAG", "" + e);
        }

        return changeFile;
    }

    private static void getTagInfo(Attributes attrs) {
        //修改默认字符集为GB18030
        attrs.setString(Tag.SpecificCharacterSet, VR.CS, "GB18030");//解决中文乱码问题
        //Log.e("TAG", "输出所有属性信息2:" + attrs);
        String patientName = attrs.getString(Tag.PatientName, "");
        //生日
        String patientBirthDate = attrs.getString(Tag.PatientBirthDate, "");
        //机构
        String institution = attrs.getString(Tag.InstitutionName, "");
        //站点
        String station = attrs.getString(Tag.StationName, "");
        //制造商
        String Manufacturer = attrs.getString(Tag.Manufacturer, "");
        //制造商模型
        String ManufacturerModelName = attrs.getString(Tag.ManufacturerModelName, "");
        //描述--心房
        String description = attrs.getString(Tag.StudyDescription, "");
        //描述--具体
        String SeriesDescription = attrs.getString(Tag.SeriesDescription, "");
        //描述时间
        String studyData = attrs.getString(Tag.StudyDate, "");
    }
}
