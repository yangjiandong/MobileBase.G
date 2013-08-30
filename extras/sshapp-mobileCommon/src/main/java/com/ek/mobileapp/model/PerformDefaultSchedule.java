package com.ek.mobileapp.model;

import java.io.Serializable;

public class PerformDefaultSchedule implements Serializable {

    private static final long serialVersionUID = -7788290546006470046L;
    private String frequency;
    private String administration;
    private String defaultSchedule;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getDefaultSchedule() {
        return defaultSchedule;
    }

    public void setDefaultSchedule(String defaultSchedule) {
        this.defaultSchedule = defaultSchedule;
    }

}