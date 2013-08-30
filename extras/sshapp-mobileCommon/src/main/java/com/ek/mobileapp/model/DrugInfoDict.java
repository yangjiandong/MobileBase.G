package com.ek.mobileapp.model;

import java.io.Serializable;

public class DrugInfoDict implements Serializable {

    private static final long serialVersionUID = 4721471040054316702L;

    private String code;
    private String name;
    private String spec;
    private String dosage;
    private String administration;
    private String frequency;
    private String unit1;
    private String unit2;
    private String unit3;
    private String toxiProperty;//毒麻精神类药品
    private String retailPrice2;//包装单位价格 零价(对应单位2)
    private String retailPrice3;//包装单位价格 零价(对应单位3)

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

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getUnit3() {
        return unit3;
    }

    public void setUnit3(String unit3) {
        this.unit3 = unit3;
    }

    public String getToxiProperty() {
        return toxiProperty;
    }

    public void setToxiProperty(String toxiProperty) {
        this.toxiProperty = toxiProperty;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getRetailPrice2() {
        return retailPrice2;
    }

    public void setRetailPrice2(String retailPrice2) {
        this.retailPrice2 = retailPrice2;
    }

    public String getRetailPrice3() {
        return retailPrice3;
    }

    public void setRetailPrice3(String retailPrice3) {
        this.retailPrice3 = retailPrice3;
    }

}