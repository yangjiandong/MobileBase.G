package com.ek.mobileapp.model;

//医嘱内容(查询用)
public class QueryOrderData {

    private String patientId;//住院号
    private String visitId;//就诊次数
    private String patientName;//病人姓名
    private String bedNo;//床位号
    private String orderId;//医嘱id
    private String orderNo;//医嘱顺序号
    private String orderSubNo;//医嘱子顺序号
    private String orderClass;
    private String nurseClass;//护理等级
    private String condition;//病情
    private String orderText;//医嘱内容
    private String freqDetail;//医嘱辅助信息(如：自备)
    private String speed;//滴速
    private String dosage;//用量
    private String unit;//单位
    private String frequency;//频次
    private String administration;//用法
    private Long orderType;//用来判断临时还是长期
    private String performSchedule;//频次具体描述用来判断周
    private String executeName;//执行人
    private String startDateTime;//医嘱开始日期
    private String endDateTime;//医嘱停止日期
    private String addDate;

    private String doctorSign;//医生签名
    private String nurseSign;//护士签名
    private String stopDoctorSign;//停止医嘱医生签名
    private String stopNurseSign;//停止医嘱护士签名

    private String checkName;//开始核对
    private String checkGroupName;//停止核对

    private String orderAdditional;//嘱托(具体的文字信息)

    private String selected;//是否选中
    private String groupOrdersFlag;//组医嘱标记，用于颜色标识
    private String isExecuted;//标记医嘱是否被执行过

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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
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

    public String getOrderClass() {
        return orderClass;
    }

    public void setOrderClass(String orderClass) {
        this.orderClass = orderClass;
    }

    public String getNurseClass() {
        return nurseClass;
    }

    public void setNurseClass(String nurseClass) {
        this.nurseClass = nurseClass;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
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

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
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

    public String getExecuteName() {
        return executeName;
    }

    public void setExecuteName(String executeName) {
        this.executeName = executeName;
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

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getCheckGroupName() {
        return checkGroupName;
    }

    public void setCheckGroupName(String checkGroupName) {
        this.checkGroupName = checkGroupName;
    }

    public String getOrderAdditional() {
        return orderAdditional;
    }

    public void setOrderAdditional(String orderAdditional) {
        this.orderAdditional = orderAdditional;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getGroupOrdersFlag() {
        return groupOrdersFlag;
    }

    public void setGroupOrdersFlag(String groupOrdersFlag) {
        this.groupOrdersFlag = groupOrdersFlag;
    }

    public String getIsExecuted() {
        return isExecuted;
    }

    public void setIsExecuted(String isExecuted) {
        this.isExecuted = isExecuted;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}