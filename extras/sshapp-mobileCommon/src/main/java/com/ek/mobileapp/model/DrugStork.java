package com.ek.mobileapp.model;

import java.io.Serializable;

public class DrugStork implements Serializable {

    private static final long serialVersionUID = 1898629884783132576L;
    private String code;
    private String type;//区分门诊还是住院（1-门诊  2-住院）
    private String deptCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

}