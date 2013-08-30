package com.ek.mobileapp.model;

import java.io.Serializable;

public class Frequency implements Serializable {

    private static final long serialVersionUID = -5168158450684695575L;
    private String code;
    private String name;
    private int value;
    private String freqCounter;//频率次数
    private String freqInterval;//频率间隔
    private String freqIntervalUnits;//频率间隔单位

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getFreqCounter() {
        return freqCounter;
    }

    public void setFreqCounter(String freqCounter) {
        this.freqCounter = freqCounter;
    }

    public String getFreqInterval() {
        return freqInterval;
    }

    public void setFreqInterval(String freqInterval) {
        this.freqInterval = freqInterval;
    }

    public String getFreqIntervalUnits() {
        return freqIntervalUnits;
    }

    public void setFreqIntervalUnits(String freqIntervalUnits) {
        this.freqIntervalUnits = freqIntervalUnits;
    }
}