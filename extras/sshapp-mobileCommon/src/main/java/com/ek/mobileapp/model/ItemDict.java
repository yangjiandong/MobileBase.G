package com.ek.mobileapp.model;

import java.io.Serializable;

public class ItemDict extends CommonDict implements Serializable {

    private static final long serialVersionUID = 3961811249848653366L;

    private String itemClass;

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

}