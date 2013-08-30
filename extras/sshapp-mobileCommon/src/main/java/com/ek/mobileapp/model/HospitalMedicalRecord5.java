package com.ek.mobileapp.model;

//住院病案首页--费用
public class HospitalMedicalRecord5 {
    private String type_name;//费用类型
    private String type_code;//费用编码
    private String total_amt;//费用

    public String getType_name() {
        return type_name;
    }
    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
    public String getType_code() {
        return type_code;
    }
    public void setType_code(String type_code) {
        this.type_code = type_code;
    }
    public String getTotal_amt() {
        return total_amt;
    }
    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

}
