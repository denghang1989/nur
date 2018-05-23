package szszhospital.cn.com.mobilenurse.remote.response;

public class AuditDetailResponse {

    /**
     * RetCode : 0
     * RetDesc : 配药成功！
     */

    public int    RetCode;
    public String RetDesc;

    @Override
    public String toString() {
        return "AuditDetailResponse{" +
                "RetCode='" + RetCode + '\'' +
                ", RetDesc='" + RetDesc + '\'' +
                '}';
    }
}
