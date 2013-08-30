package com.ek.mobileapp.model;

import java.io.Serializable;

//通用字典
//drugdict,itemdict,operationdict
public class CommonDict implements Serializable {

    private static final long serialVersionUID = 8773359977381167146L;

    private String code;
    private String name;
    private String pycode;
    private String wbcode;
    private String type;//用于存储类别、等级等其他信息

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPycode() {
        return pycode;
    }

    public void setPycode(String pycode) {
        this.pycode = pycode;
    }

    public String getWbcode() {
        return wbcode;
    }

    public void setWbcode(String wbcode) {
        this.wbcode = wbcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}