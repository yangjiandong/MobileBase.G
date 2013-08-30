package com.ek.mobileapp.model;

//新开医嘱内容
public class DoctorOrders {

    private String patientId;//住院号
    private String visitId;//就诊次数
    private String orderId;//医嘱id
    private String orderNo;//医嘱顺序号
    private String orderSubNo;//医嘱子顺序号
    private String orderText;//医嘱内容
    private String orderClass;//
    private String freqDetail;//医嘱辅助信息(如：自备)
    private String dosage;//用量
    private String dosageUnits;//单位
    private String frequency;//频次
    private Long repeatIndicator;//用来判断临时还是长期
    private String performSchedule;//频次具体描述用来判断周
    private String administration;//用法
    private String freqCounter;
    private String freqInterval;
    private String freqIntervalUnit;
    private String startDateTime;//医嘱开始日期
    private String enterDateTime;
    private String orderStatus;
    private String prescType;

    private String orderType;
    private String classType;

    private String orderCode;//字典代码
    private String billingAttr;//反映本条医嘱计价方面的信息。0-正常计价 1-自带药 2-需手工计价 3-不计价

    private String groupOrdersFlag;//组医嘱标记，用于颜色标识

    private String isnewOrder;//标记是否为新增，1为新增，0为已保存

    private String orderingDept;//开医嘱科室
    private String doctor;//开医嘱医生
    private String empNo;//开医嘱医生工号
    private String printIndicator;//表示该医嘱是否已打印医嘱本，0-未打印，1-已打印
    private String fclass;//医嘱大类A-药品 Z-检疗
    private String clinicoderSubNo;//用于记录检验、检查、手术申请单项目序号

    private String relatedOrderNo;//用于记录停止医嘱作用的OrderNo
    private String relatedOrderSubNo;//用于记录停止医嘱作用的OrderSubNo
    private String stopIndicator;//停止医嘱标志

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

    public String getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(String orderClass) {
        this.orderClass = orderClass;
    }

    public String getDosageUnits() {
        return dosageUnits;
    }

    public void setDosageUnits(String dosageUnits) {
        this.dosageUnits = dosageUnits;
    }

    public Long getRepeatIndicator() {
        return repeatIndicator;
    }

    public void setRepeatIndicator(Long repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }

    public String getFreqCounter() {
        return freqCounter;
    }

    public void setFreqCounter(String freqCounter) {
        this.freqCounter = freqCounter;
    }

    public String getFreqInterval() {
        return freqInterval;
    }

    public void setFreqInterval(String freqInterval) {
        this.freqInterval = freqInterval;
    }

    public String getFreqIntervalUnit() {
        return freqIntervalUnit;
    }

    public void setFreqIntervalUnit(String freqIntervalUnit) {
        this.freqIntervalUnit = freqIntervalUnit;
    }

    public String getEnterDateTime() {
        return enterDateTime;
    }

    public void setEnterDateTime(String enterDateTime) {
        this.enterDateTime = enterDateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPrescType() {
        return prescType;
    }

    public void setPrescType(String prescType) {
        this.prescType = prescType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getBillingAttr() {
        return billingAttr;
    }

    public void setBillingAttr(String billingAttr) {
        this.billingAttr = billingAttr;
    }

    public String getIsnewOrder() {
        return isnewOrder;
    }

    public void setIsnewOrder(String isnewOrder) {
        this.isnewOrder = isnewOrder;
    }

    public String getOrderingDept() {
        return orderingDept;
    }

    public void setOrderingDept(String orderingDept) {
        this.orderingDept = orderingDept;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getPrintIndicator() {
        return printIndicator;
    }

    public void setPrintIndicator(String printIndicator) {
        this.printIndicator = printIndicator;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public String getClinicoderSubNo() {
        return clinicoderSubNo;
    }

    public void setClinicoderSubNo(String clinicoderSubNo) {
        this.clinicoderSubNo = clinicoderSubNo;
    }

    public String getRelatedOrderNo() {
        return relatedOrderNo;
    }

    public void setRelatedOrderNo(String relatedOrderNo) {
        this.relatedOrderNo = relatedOrderNo;
    }

    public String getRelatedOrderSubNo() {
        return relatedOrderSubNo;
    }

    public void setRelatedOrderSubNo(String relatedOrderSubNo) {
        this.relatedOrderSubNo = relatedOrderSubNo;
    }

    public String getStopIndicator() {
        return stopIndicator;
    }

    public void setStopIndicator(String stopIndicator) {
        this.stopIndicator = stopIndicator;
    }

}