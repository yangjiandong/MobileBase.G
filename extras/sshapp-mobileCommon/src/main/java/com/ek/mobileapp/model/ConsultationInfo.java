package com.ek.mobileapp.model;

//会诊信息
public class ConsultationInfo {

    private String applyNo;//申请单号
    private String patientId;//住院号

    private String appleType;//申请等级 --急、普
    private String applyRemark;//病历摘要

    private String applyRequest;//请求会诊目的和要求
    private String hospital;//会诊医院
    private String diagDept;//会诊科室
    private String applyDate;//申请会诊时间
    private String applyDoctor;//请求会诊医师
    private String diagDoctor;//会诊医师
    private String selectFlag;//会诊状态 --待会诊，已会诊
    private String visitId;//住院次数
    public String getApplyNo() {
        return applyNo;
    }
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getAppleType() {
        return appleType;
    }
    public void setAppleType(String appleType) {
        this.appleType = appleType;
    }
    public String getApplyRemark() {
        return applyRemark;
    }
    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }
    public String getApplyRequest() {
        return applyRequest;
    }
    public void setApplyRequest(String applyRequest) {
        this.applyRequest = applyRequest;
    }
    public String getHospital() {
        return hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    public String getDiagDept() {
        return diagDept;
    }
    public void setDiagDept(String diagDept) {
        this.diagDept = diagDept;
    }
    public String getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    public String getApplyDoctor() {
        return applyDoctor;
    }
    public void setApplyDoctor(String applyDoctor) {
        this.applyDoctor = applyDoctor;
    }
    public String getDiagDoctor() {
        return diagDoctor;
    }
    public void setDiagDoctor(String diagDoctor) {
        this.diagDoctor = diagDoctor;
    }
    public String getSelectFlag() {
        return selectFlag;
    }
    public void setSelectFlag(String selectFlag) {
        this.selectFlag = selectFlag;
    }
    public String getVisitId() {
        return visitId;
    }
    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

}