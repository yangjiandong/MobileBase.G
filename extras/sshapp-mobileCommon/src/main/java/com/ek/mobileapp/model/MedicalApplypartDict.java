package com.ek.mobileapp.model;

import java.io.Serializable;

//检查部位字典（也用于存储已选检查部位）
public class MedicalApplypartDict implements Serializable {
    private String itemCode;//检查部位号
    private String partName;//检查部位名称
    private String examNo;//项目申请单号
    private String itemNo;//检查申请子序号

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

}