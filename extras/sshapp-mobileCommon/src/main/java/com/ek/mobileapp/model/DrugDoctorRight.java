package com.ek.mobileapp.model;

import java.io.Serializable;

public class DrugDoctorRight implements Serializable {
    private static final long serialVersionUID = 7901380450101996696L;

    private String code;
    private String userLogin;
    private String rightType;
    private String itemClass;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getRightType() {
        return rightType;
    }

    public void setRightType(String rightType) {
        this.rightType = rightType;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

}