package szszhospital.cn.com.mobilenurse.remote.model;

import java.util.Objects;

public class Patient {

    public String wardDesc;
    public String bedCode;
    public String patName;
    public String regNo;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Patient))
            return false;
        Patient patient = (Patient) o;
        return Objects.equals(regNo, patient.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
}
