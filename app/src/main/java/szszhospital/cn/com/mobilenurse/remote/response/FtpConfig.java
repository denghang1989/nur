package szszhospital.cn.com.mobilenurse.remote.response;

public class FtpConfig {

    /**
     * {"FtpIP":"172.18.0.27","FtpPort":"21","FtpUserName":"Anonymous"}
     * FtpIP : 172.18.0.27
     * FtpPort : 21
     * FtpUserName : Anonymous
     */

    public String FtpIP           = "172.18.0.27";
    public String FtpPort         = "21";
    public String FtpUserName     = "Anonymous";
    public String FtpUserPassWord = "";


    @Override
    public String toString() {
        return "FtpConfig{" +
                "FtpIP='" + FtpIP + '\'' +
                ", FtpPort='" + FtpPort + '\'' +
                ", FtpUserName='" + FtpUserName + '\'' +
                ", FtpUserPassWord='" + FtpUserPassWord + '\'' +
                '}';
    }
}
