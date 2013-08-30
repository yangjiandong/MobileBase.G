package com.ek.mobileapp.model;

//生命体征-按时间点明细
public class TZMX {
    public static final int KB = 1;
    public static final int GB = 2;
    public static final int YB = 3;

    private float tw;
    private int twl;
    private float mb;
    private float xl;
    private float hx;
    private int tp;
    public void setTw(float tw) {
        this.tw = tw;
    }
    public float getTw() {
        return tw;
    }
    public void setTwl(int twl) {
        this.twl = twl;
    }
    public int getTwl() {
        return twl;
    }
    public void setMb(float mb) {
        this.mb = mb;
    }
    public float getMb() {
        return mb;
    }
    public void setXl(float xl) {
        this.xl = xl;
    }
    public float getXl() {
        return xl;
    }
    public void setHx(float hx) {
        this.hx = hx;
    }
    public float getHx() {
        return hx;
    }
    public void setTp(int tp) {
        this.tp = tp;
    }
    public int getTp() {
        return tp;
    }
}
