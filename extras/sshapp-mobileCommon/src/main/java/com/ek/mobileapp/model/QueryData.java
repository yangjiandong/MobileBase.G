package com.ek.mobileapp.model;

import java.math.BigDecimal;

///用于存储全院概况
public class QueryData {

    private Long id;

    private String busDate;

    private Long hospId;

    private String hospName;

    private Long itemId;

    private String deptCode;

    private Long value;

    private Long lastValue;

    private String deptName;

    private String itemName;

    private String isleaf;

    private String selected;//是否选中

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusDate() {
        return busDate;
    }

    public void setBusDate(String busDate) {
        this.busDate = busDate;
    }

    public Long getHospId() {
        return hospId;
    }

    public void setHospId(Long hospId) {
        this.hospId = hospId;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getHospName() {
        return hospName;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getLastValue() {
        return lastValue;
    }

    public void setLastValue(Long lastValue) {
        this.lastValue = lastValue;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(String isleaf) {
        this.isleaf = isleaf;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}