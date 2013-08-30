package com.ek.mobileapp.model;

//住院病案首页--诊断信息
public class HospitalMedicalRecord2 {
    private String hmr16_1;//诊断类别
    private String hmr16_2;//诊断
    private String hmr16_3;//疾病编码
    private String hmr16_4;//疾病编码
    private String hmr47_1;//治愈情况

    public String getHmr16_1() {
        return hmr16_1;
    }

    public void setHmr16_1(String hmr16_1) {
        this.hmr16_1 = hmr16_1;
    }

    public String getHmr16_2() {
        if (hmr16_2 == null) {
            return " - ";
        } else {
            return hmr16_2;
        }
    }

    public void setHmr16_2(String hmr16_2) {
        this.hmr16_2 = hmr16_2;
    }

    public String getHmr16_3() {
        if (hmr16_3 == null) {
            return " - ";
        } else {
            return hmr16_3;
        }
    }

    public void setHmr16_3(String hmr16_3) {
        this.hmr16_3 = hmr16_3;
    }

    public String getHmr16_4() {
        return hmr16_4;
    }

    public void setHmr16_4(String hmr16_4) {
        this.hmr16_4 = hmr16_4;
    }

    public String getHmr47_1() {
        if (hmr47_1 == null) {
            return "";
        } else {
            return hmr47_1;
        }
    }

    public void setHmr47_1(String hmr47_1) {
        this.hmr47_1 = hmr47_1;
    }

}
