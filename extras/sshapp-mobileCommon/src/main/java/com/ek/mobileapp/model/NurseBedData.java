package com.ek.mobileapp.model;

import java.io.Serializable;

//护士,床边巡视
public class NurseBedData implements Serializable {
    private static final long serialVersionUID = -218624785057080367L;
    private Long id;
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String orderText;//内容
    private Long userId;//用户id
    private String userName;//用户姓名
    private String addTime;//操作时间
    private String state; //Y已提交,N未提交
    private String sameTimesFlag;//同一时间归类，用于颜色标记

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

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSameTimesFlag() {
        return sameTimesFlag;
    }

    public void setSameTimesFlag(String sameTimesFlag) {
        this.sameTimesFlag = sameTimesFlag;
    }
}