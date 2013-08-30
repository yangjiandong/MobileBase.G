package com.ek.mobileapp.model;

import java.io.Serializable;

//制式检验单
public class MedicalJyGroupdict implements Serializable {

    private String className;//检验单名称
    private String itemCode;//检验项目代码
    private String itemName;//检验项目名称

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}