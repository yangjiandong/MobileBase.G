package com.ek.mobileapp.model;

//输血信息详细
public class BloodInfoDetail {

    private String appleNum;//申请单号
    private String matchSubnum;//申请单子号
    private String fastSlow;//用血安排
    private String transDate;//预定输血时间
    private String transCapacity;//输血量
    private String bloodType;//申请成份血
    private String operator;//操作者
    private String unit;//用血单位
    public String getAppleNum() {
        return appleNum;
    }
    public void setAppleNum(String appleNum) {
        this.appleNum = appleNum;
    }
    public String getMatchSubnum() {
        return matchSubnum;
    }
    public void setMatchSubnum(String matchSubnum) {
        this.matchSubnum = matchSubnum;
    }
    public String getFastSlow() {
        return fastSlow;
    }
    public void setFastSlow(String fastSlow) {
        this.fastSlow = fastSlow;
    }
    public String getTransDate() {
        return transDate;
    }
    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
    public String getTransCapacity() {
        return transCapacity;
    }
    public void setTransCapacity(String transCapacity) {
        this.transCapacity = transCapacity;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }

}