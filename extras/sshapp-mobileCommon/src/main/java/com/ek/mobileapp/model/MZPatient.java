package com.ek.mobileapp.model;

public class MZPatient {

    private String cardId;//卡号
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String patName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String registClinic;//挂号诊别
    private String clinicNo;//诊号
    private String registTime;//挂号时间

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
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

    public String getRegistClinic() {
        return registClinic;
    }

    public void setRegistClinic(String registClinic) {
        this.registClinic = registClinic;
    }

    public String getClinicNo() {
        return clinicNo;
    }

    public void setClinicNo(String clinicNo) {
        this.clinicNo = clinicNo;
    }

    public String getRegistTime() {
        return registTime;
    }

    public void setRegistTime(String registTime) {
        this.registTime = registTime;
    }
}