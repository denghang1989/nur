package szszhospital.cn.com.mobilenurse.remote.request;

/**
 * 发药单明细列表
 */
public class DispDetailListRequest {
    public String className  = "web.DHCPH.PDA.DrugAudit";
    public String methodName = "GetDispDetailList";
    public String type       = "Method";
    public String AuditDr;
}
