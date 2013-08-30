package com.ek.mobileapp.model;

import java.math.BigDecimal;

///用于显示图表
public class ChartData {

    private String name;

    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}