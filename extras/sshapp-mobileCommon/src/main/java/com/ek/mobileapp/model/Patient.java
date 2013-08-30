package com.ek.mobileapp.model;

import java.io.Serializable;

public class Patient implements Serializable {

    private static final long serialVersionUID = 3433005367333370652L;

    private Long id;
    private Long userId;
    private String patientId;//住院号
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String bedNo;//床位号
    private String wardCode;//病区
    private String deptName;//病区
    private String deptCode;//专科代码
    private String deptName2;//专科
    private String doctorName;//医生
    private String condition;
    private String nurseClass;
    private String hospitalNo;
    private String visitId;
    private String isNewOrder;
    private String admissDate;
    private String isFever;//Y发热
    private String clinicPath;//临床路径id(为空则没有进入临床路径)
    private String clinicPathName;//临床路径名称(为空则没有进入临床路径)
    private String diseaseCodeIncome;//入院疾病代码
    private String diagIncome;//入院诊断
    private String lczd;//临床诊断

    private String chargeType;//费别
    private String zyts;//住院天数
    private String cyjz;//出院结账标志 0-在院 1-出院

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNurseClass() {
        return nurseClass;
    }

    public void setNurseClass(String nurseClass) {
        this.nurseClass = nurseClass;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getHospitalNo() {
        return hospitalNo;
    }

    public void setHospitalNo(String hospitalNo) {
        this.hospitalNo = hospitalNo;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getIsNewOrder() {
        return isNewOrder;
    }

    public void setIsNewOrder(String isNewOrder) {
        this.isNewOrder = isNewOrder;
    }

    public String getAdmissDate() {
        return admissDate;
    }

    public void setAdmissDate(String admissDate) {
        this.admissDate = admissDate;
    }

    public String getIsFever() {
        return isFever;
    }

    public void setIsFever(String isFever) {
        this.isFever = isFever;
    }

    public String getClinicPath() {
        return clinicPath;
    }

    public void setClinicPath(String clinicPath) {
        this.clinicPath = clinicPath;
    }

    public String getDiseaseCodeIncome() {
        return diseaseCodeIncome;
    }

    public void setDiseaseCodeIncome(String diseaseCodeIncome) {
        this.diseaseCodeIncome = diseaseCodeIncome;
    }

    public String getDiagIncome() {
        return diagIncome;
    }

    public void setDiagIncome(String diagIncome) {
        this.diagIncome = diagIncome;
    }

    public String getLczd() {
        return lczd;
    }

    public void setLczd(String lczd) {
        this.lczd = lczd;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getClinicPathName() {
        return clinicPathName;
    }

    public void setClinicPathName(String clinicPathName) {
        this.clinicPathName = clinicPathName;
    }

    public String getZyts() {
        return zyts;
    }

    public void setZyts(String zyts) {
        this.zyts = zyts;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName2() {
        return deptName2;
    }

    public void setDeptName2(String deptName2) {
        this.deptName2 = deptName2;
    }

    public String getCyjz() {
        return cyjz;
    }

    public void setCyjz(String cyjz) {
        this.cyjz = cyjz;
    }

}