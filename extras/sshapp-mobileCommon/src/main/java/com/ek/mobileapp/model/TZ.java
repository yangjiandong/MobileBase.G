package com.ek.mobileapp.model;

import java.util.ArrayList;
import java.util.List;

//生命体征
public class TZ {
    private String rl = "";//入量
    private String db = "";//大便
    private String nl = "";//尿量
    private String pcqt = "";//排出量其他
    private String xyl = "";//血压低
    private String xyh = "";//血压高
    private String tz = "";//体重
    private String ps1 = "";//皮试1
    private String ps2 = "";//皮试2
    private String qt = "";//其他
    private String date = "";//日期
    private int inday;//住院日数
    private int ssday;//手术后日数

    private List<TZMX> tzmxs = new ArrayList<TZMX>();

    public void setRl(String rl) {
        this.rl = rl;
    }

    public String getRl() {
        return rl;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getDb() {
        return db;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getNl() {
        return nl;
    }

    public void setPcqt(String pcqt) {
        this.pcqt = pcqt;
    }

    public String getPcqt() {
        return pcqt;
    }

    public void setXyl(String xyl) {
        this.xyl = xyl;
    }

    public String getXyl() {
        return xyl;
    }

    public void setXyh(String xyh) {
        this.xyh = xyh;
    }

    public String getXyh() {
        return xyh;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getTz() {
        return tz;
    }

    public void setPs1(String ps1) {
        this.ps1 = ps1;
    }

    public String getPs1() {
        return ps1;
    }

    public void setPs2(String ps2) {
        this.ps2 = ps2;
    }

    public String getPs2() {
        return ps2;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getQt() {
        return qt;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTzmxs(List<TZMX> tzmxs) {
        this.tzmxs = tzmxs;
    }

    public List<TZMX> getTzmxs() {
        return tzmxs;
    }

    public int getInday() {
        return inday;
    }

    public void setInday(int inday) {
        this.inday = inday;
    }

    public int getSsday() {
        return ssday;
    }

    public void setSsday(int ssday) {
        this.ssday = ssday;
    }

}
