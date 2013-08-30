package com.ek.mobileapp.model;

import java.io.Serializable;

public class OutPatient implements Serializable {

    private static final long serialVersionUID = -2565491631419791424L;
    private String patientId;//住院号
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄

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

}