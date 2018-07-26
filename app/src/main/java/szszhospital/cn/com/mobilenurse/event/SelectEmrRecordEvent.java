package szszhospital.cn.com.mobilenurse.event;

import szszhospital.cn.com.mobilenurse.remote.response.EMREposideInfo;

public class SelectEmrRecordEvent {
    public EMREposideInfo data;

    public SelectEmrRecordEvent(EMREposideInfo data) {
        this.data = data;
    }
}
