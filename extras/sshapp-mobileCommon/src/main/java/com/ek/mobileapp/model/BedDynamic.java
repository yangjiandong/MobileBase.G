package com.ek.mobileapp.model;

public class BedDynamic {
    private String hospCode;//院区编号
    private String hospName;//院区名称
    private String deptCode;//病区编号
    private String deptName;//病区名称
    private String kbBed;//核编床位
    private String szPatient;//收住病人
    private String zbBed;//在编空床
    private String bedNo;//床号
    private String bedClass;//床位等级
    private String bedType;//床位类型

    public String getHospCode() {
        return hospCode;
    }
    public void setHospCode(String hospCode) {
        this.hospCode = hospCode;
    }
    public String getHospName() {
        return hospName;
    }
    public void setHospName(String hospName) {
        this.hospName = hospName;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getKbBed() {
        return kbBed;
    }
    public void setKbBed(String kbBed) {
        this.kbBed = kbBed;
    }
    public String getSzPatient() {
        return szPatient;
    }
    public void setSzPatient(String szPatient) {
        this.szPatient = szPatient;
    }
    public String getZbBed() {
        return zbBed;
    }
    public void setZbBed(String zbBed) {
        this.zbBed = zbBed;
    }
    public String getBedNo() {
        return bedNo;
    }
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }
    public String getBedClass() {
        return bedClass;
    }
    public void setBedClass(String bedClass) {
        this.bedClass = bedClass;
    }
    public String getBedType() {
        return bedType;
    }
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }
}