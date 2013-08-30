package com.ek.mobileapp.model;

import java.math.BigDecimal;

//病人健康调查
public class PatientNutriInfos {

    private String patientId;
    private String visitId;
    private String patientName;
    private String sex;
    private int age;
    private String ageUnit;
    private String tall;
    private String weight;
    private String bmi;
    private String actType;
    private int foodType;
    private int nutritionManage;//营养成分摄入控制:0不控制,1控制
    private int drisType;//0推荐摄入量,1平均需要量,2可耐受最高摄入量
    private BigDecimal floatValue;//参考值的浮动值,默认100,百分比
    private String pregnant;//

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    public int getNutritionManage() {
        return nutritionManage;
    }

    public void setNutritionManage(int nutritionManage) {
        this.nutritionManage = nutritionManage;
    }

    public int getDrisType() {
        return drisType;
    }

    public void setDrisType(int drisType) {
        this.drisType = drisType;
    }

    public BigDecimal getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(BigDecimal floatValue) {
        this.floatValue = floatValue;
    }

    public String getPregnant() {
        return pregnant;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }

}