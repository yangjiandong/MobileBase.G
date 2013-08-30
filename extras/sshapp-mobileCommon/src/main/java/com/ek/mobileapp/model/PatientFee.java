package com.ek.mobileapp.model;

///病人费用
public class PatientFee {

    private Long id;
    private Long userId;
    private String patientId;//住院号
    private String item;//费用类别
    private String costs;//费用
    private String item2;//费用类别2
    private String costs2;//费用2

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCosts() {
        return costs;
    }

    public void setCosts(String costs) {
        this.costs = costs;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getCosts2() {
        return costs2;
    }

    public void setCosts2(String costs2) {
        this.costs2 = costs2;
    }

}