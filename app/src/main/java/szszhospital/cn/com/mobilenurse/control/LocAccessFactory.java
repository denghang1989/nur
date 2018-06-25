package szszhospital.cn.com.mobilenurse.control;

public class LocAccessFactory {

    public static LocAccess getInstance(String locType) {
        String type = locType == null ? "" : locType;
        LocAccess access = null;
        switch (type) {
            case "E":
                access = new ELocAccess();
                break;
            case "":
                access = new DefaultLocAccess();
                break;
            default:
                access = new DefaultLocAccess();
                break;
        }
        return access;
    }
}
