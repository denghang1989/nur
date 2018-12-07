package szszhospital.cn.com.mobilenurse.utils;

import android.util.Log;

import com.blankj.utilcode.util.CloseUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Response;

public class FileDownUtil {
    private static final String TAG = "FileDownUtil";

    /**
     * @param url
     * @param callback
     * @desc : 下载ftp文件
     */
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
                File file = new File(Contants.PDF_DOWNLOAD_PATH, name);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                byte[] buff = new byte[1024 * 10];
                int length = 0;
                while ((length = bis.read(buff)) > 0) {
                    bos.write(buff, 0, length);
                }
                // 关闭流
                CloseUtils.closeIOQuietly(bos, bis);
                // 6. 回调
                if (callback != null) {
                    callback.success(file);
                }
            }
            // 5. 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null) {
                callback.error(e);
            }
        }
    }


    /**
     * @param url
     * @param callback
     * @desc : 下载dcm文件
     */
    public static void downFileAndChangedPng(String url, String des, FileCallback callback) {
        try {
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
                File file = new File(des);
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                byte[] buff = new byte[1024 * 10];
                int length = 0;
                while ((length = bis.read(buff)) > 0) {
                    bos.write(buff, 0, length);
                }
                // 关闭流
                CloseUtils.closeIOQuietly(bos, bis);
                // 转换图片
                File filePng = DcmUtil.readFile(file.getAbsolutePath());
                // 6. 回调
                if (callback != null) {
                    callback.success(filePng);
                }
            }
            // 5. 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            if (callback != null) {
                callback.error(e);
            }
        }
    }

    /**
     * @param url
     * @param callback
     * @desc : 下载dcm文件
     */
    public static void downFilePng(String url, String des, FileCallback callback) {
        Log.d(TAG, "downFilePng: " + url);
        try {
            Response execute = OkHttpUtils.get().
                    url(Contants.PACS_PNG).
                    addParams("ftpPath", url).
                    build().execute();
            if (execute.isSuccessful()) {
                InputStream inputStream = execute.body().byteStream();
                if (inputStream != null) {
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    File file = new File(des);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] buff = new byte[1024 * 10];
                    int length = 0;
                    while ((length = bis.read(buff)) > 0) {
                        bos.write(buff, 0, length);
                    }
                    // 关闭流
                    CloseUtils.closeIOQuietly(bos, bis);
                    // 6. 回调
                    if (callback != null) {
                        callback.success(file);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
