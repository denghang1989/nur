package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 发药单
 */
public class DrugBillListRequest {
    public String className  = "web.DHCPH.PDA.DrugAudit";
    public String methodName = "GetDispList";
    public String type       = "Method";
    public String Loc;
    public String Flag;
    public String User;

    @Override
    public String toString() {
        return "DrugBillListRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", type='" + type + '\'' +
                ", Loc='" + Loc + '\'' +
                ", Flag='" + Flag + '\'' +
                ", User='" + User + '\'' +
                '}';
    }
}
