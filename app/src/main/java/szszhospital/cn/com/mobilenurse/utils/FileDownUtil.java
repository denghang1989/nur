package szszhospital.cn.com.mobilenurse.utils;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownUtil {

    public static void downFile(String url, FileCallback callback) {
        try {
            String[] split = url.split("/");
            String name = split[split.length - 1];
            URL urls = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
            // 设置请求方式
            connection.setRequestMethod("GET");
            // 设置超时时间
            connection.setConnectTimeout(3000);
            // 连接
            connection.connect();
            // 4. 得到响应状态码的返回值 responseCode
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), name);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                byte[] buff = new byte[1024 * 10];
                int length = 0;
                while ((length = bis.read(buff)) > 0) {
                    bos.write(buff, 0, length);
                }
                bos.close();
                bis.close();
                // 6. 回调
                callback.success(file);
            }
            // 5. 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            callback.error(e);
        }
    }
}
