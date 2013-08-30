package com.ek.mobileapp.model;

import java.io.Serializable;

//压疮评估单
public class AssessItem01 implements Serializable {
    private static final long serialVersionUID = -2697924237117090457L;

    private Long id;
    private Long userId;//用户id
    private String userName;//用户姓名
    private String patientId;//住院号
    private String visitId;
    private String addDate;//保存日期
    private String state;//标记是否进行过保存,Y则提交给his
    //
    private int b_perceive;//感知
    private int b_moist;//潮湿
    private int b_active;//活动
    private int b_move;//移动
    private int b_diet;//营养
    private int b_friction;//摩擦力
    private int b_all;//总分
    private String note;//压疮发生危险

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getB_perceive() {
        return b_perceive;
    }

    public void setB_perceive(int b_perceive) {
        this.b_perceive = b_perceive;
    }

    public int getB_moist() {
        return b_moist;
    }

    public void setB_moist(int b_moist) {
        this.b_moist = b_moist;
    }

    public int getB_active() {
        return b_active;
    }

    public void setB_active(int b_active) {
        this.b_active = b_active;
    }

    public int getB_move() {
        return b_move;
    }

    public void setB_move(int b_move) {
        this.b_move = b_move;
    }

    public int getB_diet() {
        return b_diet;
    }

    public void setB_diet(int b_diet) {
        this.b_diet = b_diet;
    }

    public int getB_friction() {
        return b_friction;
    }

    public void setB_friction(int b_friction) {
        this.b_friction = b_friction;
    }

    public int getB_all() {
        return b_all;
    }

    public void setB_all(int b_all) {
        this.b_all = b_all;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}