package com.ek.mobileapp.model;

import java.io.Serializable;

public class OperationDict extends CommonDict implements Serializable {

    private static final long serialVersionUID = -6477869521114387957L;

    private String ssdj;//手术等级

    public String getSsdj() {
        return ssdj;
    }

    public void setSsdj(String ssdj) {
        this.ssdj = ssdj;
    }

}