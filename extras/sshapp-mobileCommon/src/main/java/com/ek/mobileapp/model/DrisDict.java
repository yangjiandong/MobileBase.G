package com.ek.mobileapp.model;

import java.math.BigDecimal;

//摄入营养成分标准表
public class DrisDict {

    public static final String EAR = "平均需要量";
    public static final String RNI = "推荐摄入量";
    public static final String AI = "适宜摄入量";
    public static final String UL = "可耐受最高摄入量";

    private Long id;
    private String ageName;
    private BigDecimal ageMin;
    private BigDecimal ageMax;
    private String type;
    private String sex;
    private String weight;
    private String actType;
    private Long nutritionId;
    private String nutritionName;
    private BigDecimal value;
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgeName() {
        return ageName;
    }

    public void setAgeName(String ageName) {
        this.ageName = ageName;
    }

    public BigDecimal getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(BigDecimal ageMin) {
        this.ageMin = ageMin;
    }

    public BigDecimal getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(BigDecimal ageMax) {
        this.ageMax = ageMax;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public Long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(Long nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}