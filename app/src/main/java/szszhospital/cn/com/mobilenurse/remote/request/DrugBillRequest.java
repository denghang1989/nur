package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 发药单
 */
public class DrugBillRequest {
    public String className  = "web.NurDispAudit";
    public String methodName = "GetDispInfoByDispNo";
    public String type       = "Query";
    public String dispNo;
    public String userId;

    @Override
    public String toString() {
        return "DrugBillRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", type='" + type + '\'' +
                ", dispNo='" + dispNo + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
