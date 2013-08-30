package com.ek.mobileapp.model;

import java.io.Serializable;

//医嘱内容
//是否与entity OrderData 同步?
public class OrderData implements Serializable {
    private static final long serialVersionUID = -4151644202609267592L;

    private Long id;
    private Long userId;//用户id
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String orderId;//医嘱id
    private String orderNo;//医嘱顺序号
    private String orderSubNo;//医嘱子顺序号
    private String orderClass;//医嘱项目类别（药品，检疗，手术）
    private String orderText;//医嘱内容
    private String freqDetail;//医嘱辅助信息(如：自备)
    private String dosage;//用量
    private String frequency;//频次
    private String administration;//用法
    private int value1;//总共次数
    private int value2;//当前次数
    private String state;//状态 Y表示输液中 N表示无
    private Long orderType;//用来判断临时还是长期
    private String performSchedule;//频次具体描述用来判断周
    private String startDateTime;//医嘱开始日期
    private String endDateTime;//医嘱停止日期
    private String endState;//医嘱完成标记,N未完成,Y已完成
    private String addDate;//取数日期YYYY-mm-dd
    private String isShow;//Y显示,N不显示
    private String doctorSign;//医生签名
    private String nurseSign;//护士签名
    private String groupOrdersFlag;//组医嘱标记，用于颜色标识

    //服药时，是否选中
    boolean selected = false;
    private String dosageUnits;//单位
    private String orderStatus;//医嘱状态
    private String stopDoctorSign;//停止医嘱医生签名
    private String stopNurseSign;//停止医嘱护士签名

    private String fclass;//医嘱大类A-药品 Z-检疗

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(String orderSubNo) {
        this.orderSubNo = orderSubNo;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public String getFreqDetail() {
        return freqDetail;
    }

    public void setFreqDetail(String freqDetail) {
        this.freqDetail = freqDetail;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public String getPerformSchedule() {
        return performSchedule;
    }

    public void setPerformSchedule(String performSchedule) {
        this.performSchedule = performSchedule;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getGroupOrdersFlag() {
        return groupOrdersFlag;
    }

    public void setGroupOrdersFlag(String groupOrdersFlag) {
        this.groupOrdersFlag = groupOrdersFlag;
    }

    public String getDoctorSign() {
        return doctorSign;
    }

    public void setDoctorSign(String doctorSign) {
        this.doctorSign = doctorSign;
    }

    public String getNurseSign() {
        return nurseSign;
    }

    public void setNurseSign(String nurseSign) {
        this.nurseSign = nurseSign;
    }

    public String getDosageUnits() {
        return dosageUnits;
    }

    public void setDosageUnits(String dosageUnits) {
        this.dosageUnits = dosageUnits;
    }

    public String getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(String orderClass) {
        this.orderClass = orderClass;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getStopDoctorSign() {
        return stopDoctorSign;
    }

    public void setStopDoctorSign(String stopDoctorSign) {
        this.stopDoctorSign = stopDoctorSign;
    }

    public String getStopNurseSign() {
        return stopNurseSign;
    }

    public void setStopNurseSign(String stopNurseSign) {
        this.stopNurseSign = stopNurseSign;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

}