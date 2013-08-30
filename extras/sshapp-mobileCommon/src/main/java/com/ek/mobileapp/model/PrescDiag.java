package com.ek.mobileapp.model;

//处方诊断
public class PrescDiag {

    private String prescNo;//处方号
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String diagDesc;//诊断1
    private String diagDesc2;//诊断2

    public String getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(String prescNo) {
        this.prescNo = prescNo;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagDesc2() {
        return diagDesc2;
    }

    public void setDiagDesc2(String diagDesc2) {
        this.diagDesc2 = diagDesc2;
    }

}