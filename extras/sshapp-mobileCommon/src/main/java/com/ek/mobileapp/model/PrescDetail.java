package com.ek.mobileapp.model;

//处方明细单
public class PrescDetail {

    private String prescNo;//处方号
    private String itemSno;//处方序号
    private String orderNo;//组号
    private String orderSubNo;//子组号
    private String phamCode;//药品代码
    private String phamName;//药品名称
    private String surname;//别名
    private String dosePerUnit;//含量
    private String phamSpec;//药品规格
    private String phamUnits;//总量单位
    private String quantity;//总量
    private String costs;//金额

    private String orderId;//处方序号
    private String dosage;//剂量
    private String dosageUnit;//剂量单位
    private String administration;//用法
    private String frequency;//频次
    private String days;//天数
    private String price;//价格
    private String procSchedule;//嘱托

    private String state;

    private String groupFlag;//组标记,用于标识

    public String getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(String prescNo) {
        this.prescNo = prescNo;
    }

    public String getItemSno() {
        return itemSno;
    }

    public void setItemSno(String itemSno) {
        this.itemSno = itemSno;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public String getPhamCode() {
        return phamCode;
    }

    public void setPhamCode(String phamCode) {
        this.phamCode = phamCode;
    }

    public String getPhamName() {
        return phamName;
    }

    public void setPhamName(String phamName) {
        this.phamName = phamName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDosePerUnit() {
        return dosePerUnit;
    }

    public void setDosePerUnit(String dosePerUnit) {
        this.dosePerUnit = dosePerUnit;
    }

    public String getPhamSpec() {
        return phamSpec;
    }

    public void setPhamSpec(String phamSpec) {
        this.phamSpec = phamSpec;
    }

    public String getPhamUnits() {
        return phamUnits;
    }

    public void setPhamUnits(String phamUnits) {
        this.phamUnits = phamUnits;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProcSchedule() {
        return procSchedule;
    }

    public void setProcSchedule(String procSchedule) {
        this.procSchedule = procSchedule;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

}