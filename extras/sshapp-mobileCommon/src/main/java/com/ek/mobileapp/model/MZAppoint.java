package com.ek.mobileapp.model;

public class MZAppoint {

    private String hospCode;//院区编号
    private String hospName;//院区名称
    private String deptCode;//科室编号
    private String deptName;//科室名称
    private String peopNum;//人次

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

    public String getPeopNum() {
        return peopNum;
    }

    public void setPeopNum(String peopNum) {
        this.peopNum = peopNum;
    }
}