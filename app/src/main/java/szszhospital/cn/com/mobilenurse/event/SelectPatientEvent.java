package szszhospital.cn.com.mobilenurse.event;

import szszhospital.cn.com.mobilenurse.remote.response.PatientInfo;

public class SelectPatientEvent {
    public PatientInfo mPatient;

    public SelectPatientEvent(PatientInfo item) {
        mPatient = item;
    }
}
