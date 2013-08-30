package com.ek.mobileapp.model;

import java.io.Serializable;

public class OutpCheckRecord implements Serializable {
    private static final long serialVersionUID = -2565491631419791424L;
    private String patientId;//住院号
    private String visitId;//门诊次数
    private String prescNo;//处方号
    private String itemNo;//处方行号
    private String clinicItemClass;//临床项目类别
    private String clinicItemCode;//临床项目代码
    private String operDate;//记录时间
    private Long operUser;//执行人代码
    private String performedBy;//执行科室代码
    private String psResult;//皮试结果

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

    public String getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(String prescNo) {
        this.prescNo = prescNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getClinicItemClass() {
        return clinicItemClass;
    }

    public void setClinicItemClass(String clinicItemClass) {
        this.clinicItemClass = clinicItemClass;
    }

    public String getClinicItemCode() {
        return clinicItemCode;
    }

    public void setClinicItemCode(String clinicItemCode) {
        this.clinicItemCode = clinicItemCode;
    }

    public String getOperDate() {
        return operDate;
    }

    public void setOperDate(String operDate) {
        this.operDate = operDate;
    }

    public Long getOperUser() {
        return operUser;
    }

    public void setOperUser(Long operUser) {
        this.operUser = operUser;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getPsResult() {
        return psResult;
    }

    public void setPsResult(String psResult) {
        this.psResult = psResult;
    }
}
