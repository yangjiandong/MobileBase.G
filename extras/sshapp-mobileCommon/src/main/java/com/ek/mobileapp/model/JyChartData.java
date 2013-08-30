package com.ek.mobileapp.model;

//检验报告
public class JyChartData {
    private String name;//报告日期
    private String value;//值
    private String state;//异常

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}