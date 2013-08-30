package com.ek.mobileapp.model;

//检查检验申请项目
public class MedicalApplyDetail {
    private String testNo;//检查申请单号
    private String itemNo;//检查申请子序号
    private String itemName;//项目名称
    private String itemCode;//项目his编码
    private String orderId;
    private String partflag;//可选部位数

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPartflag() {
        return partflag;
    }

    public void setPartflag(String partflag) {
        this.partflag = partflag;
    }

}