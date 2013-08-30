package com.ek.mobileapp.model;

import java.io.Serializable;

//检查、检验项目字典
public class MedicalJCApplyDict implements Serializable {
    private String examClass;//检查项目类别
    private String examSubClass;//检查项目小类别
    private String name;//项目名称
    private String code;//项目his编码
    private String pycode;
    private String partflag;//检查项目可以选择的部位数量
    private String speciman;//检验标本名称
    private String note;//检验标本名称说明

    public String getExamClass() {
        return examClass;
    }

    public void setExamClass(String examClass) {
        this.examClass = examClass;
    }

    public String getExamSubClass() {
        return examSubClass;
    }

    public void setExamSubClass(String examSubClass) {
        this.examSubClass = examSubClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPycode() {
        return pycode;
    }

    public void setPycode(String pycode) {
        this.pycode = pycode;
    }

    public String getPartflag() {
        return partflag;
    }

    public void setPartflag(String partflag) {
        this.partflag = partflag;
    }

    public String getSpeciman() {
        return speciman;
    }

    public void setSpeciman(String speciman) {
        this.speciman = speciman;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}