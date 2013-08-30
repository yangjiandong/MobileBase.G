package com.ek.mobileapp.model;

///病人费用明细
public class PatientFeeDetail {

    private Long id;
    private String patientId;//住院号
    private String visitId;
    private String itemClass;//费用类别
    private String itemName;//名称
    private String itemSpec;//规格
    private String units;//单位
    private String amount;//数量
    private String price;//单价
    private String charges;//应收费用  （考虑病人费别或特殊优惠以及特殊收费项目后病人应交的费用）
    private String billingDatetime;//生成本计价项目的日期
    private String operatorNo;//计价员
    private String orderedBy;//开单科室
    private String performedBy;//执行科室

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

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getBillingDatetime() {
        return billingDatetime;
    }

    public void setBillingDatetime(String billingDatetime) {
        this.billingDatetime = billingDatetime;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }
}