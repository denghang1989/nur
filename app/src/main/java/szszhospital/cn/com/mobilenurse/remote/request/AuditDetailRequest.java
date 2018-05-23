package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 单个药品（更新单个药品）
 */
public class AuditDetailRequest {
    public String className  = "web.DHCPH.PDA.DrugAudit";
    public String methodName = "AuditDetail";
    public String type       = "Method";
    public String Input;

    @Override
    public String toString() {
        return "AuditDetailRequest{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", type='" + type + '\'' +
                ", Input='" + Input + '\'' +
                '}';
    }
}
