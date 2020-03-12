package szszhospital.cn.com.mobilenurse.utils;

import java.io.File;

public interface FileCallback {

    void success(File file);

    void error(Exception e);
}
