package com.ek.mobileapp.model;


//病人文书
public class PatientFile {

    private String patientId;
    private String visitId;
    private String fileNo;
    private String fileName;
    private String fileDescrip;

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

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescrip() {
        return fileDescrip;
    }

    public void setFileDescrip(String fileDescrip) {
        this.fileDescrip = fileDescrip;
    }

}