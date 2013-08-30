package com.ek.mobileapp.model;

///病人手术时间
public class PatientSsDate {

    private String patientId;//住院号
    private String inDate;//手术时间
    private String outDate;//回室时间

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

}