package com.ek.mobileapp.model;

//手术信息
public class OperationInfo {

    private String scheduleId;//手术安排标识
    private String patientId;//住院号
    private String scheduledDatetime;//预约手术日期及时间
    private String operatingRoom;//手术室
    private String sequence;//台次
    private String diagbeforeOperation;//术前主要诊断
    private String patientCondition;//病情说明

    private String isolationIndicator;//隔离标志
    private String operationScale;//手术等级
    private String operatingDept;//手术科室
    private String surgeon;//手术医师姓名

    private String firstAssistant;//第一手术助手姓名
    private String secondAssistant;//第二手术助手姓名
    private String thirdAssistant;//第三手术助手姓名
    private String fourthAssistant;//第四手术助手姓名

    private String anesthesiaDoctor;//麻醉医师
    private String anesthesiaMethod;//麻醉方法
    private String reqDatetime;//申请时间
    private String notesonOperation;//备注术前准备

    private String enteredBy;//录入者
    private String examineMind;//审批意见
    private String examineOperator;//审批人
    private String visitId;//住院次数
    private String bedNo;
    private String reqDept;//申请科室
    private String operatingRoomNo;//手术间
    private String operatingName;//手术名称（用于手术安排显示）
    private String confirmFlag;//审批状态    0-待审批  1-审批通过  2-审批不通过
    private String confirmDate;//审批时间
    private String arrangeDatetime;//安排时间（由手术室回写）
    private String empNo;//录入者id
    private String sslb;//手术类别（0 择期 1 急诊）

    private String name;//病人姓名
    private String wardName;//病区代码
    private String chargeType;//结账方式
    private String sex;
    private String rysj;


    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getScheduledDatetime() {
        return scheduledDatetime;
    }

    public void setScheduledDatetime(String scheduledDatetime) {
        this.scheduledDatetime = scheduledDatetime;
    }

    public String getOperatingRoom() {
        return operatingRoom;
    }

    public void setOperatingRoom(String operatingRoom) {
        this.operatingRoom = operatingRoom;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getDiagbeforeOperation() {
        return diagbeforeOperation;
    }

    public void setDiagbeforeOperation(String diagbeforeOperation) {
        this.diagbeforeOperation = diagbeforeOperation;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getIsolationIndicator() {
        return isolationIndicator;
    }

    public void setIsolationIndicator(String isolationIndicator) {
        this.isolationIndicator = isolationIndicator;
    }

    public String getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(String operationScale) {
        this.operationScale = operationScale;
    }

    public String getOperatingDept() {
        return operatingDept;
    }

    public void setOperatingDept(String operatingDept) {
        this.operatingDept = operatingDept;
    }

    public String getSurgeon() {
        return surgeon;
    }

    public void setSurgeon(String surgeon) {
        this.surgeon = surgeon;
    }

    public String getFirstAssistant() {
        return firstAssistant;
    }

    public void setFirstAssistant(String firstAssistant) {
        this.firstAssistant = firstAssistant;
    }

    public String getSecondAssistant() {
        return secondAssistant;
    }

    public void setSecondAssistant(String secondAssistant) {
        this.secondAssistant = secondAssistant;
    }

    public String getThirdAssistant() {
        return thirdAssistant;
    }

    public void setThirdAssistant(String thirdAssistant) {
        this.thirdAssistant = thirdAssistant;
    }

    public String getFourthAssistant() {
        return fourthAssistant;
    }

    public void setFourthAssistant(String fourthAssistant) {
        this.fourthAssistant = fourthAssistant;
    }

    public String getAnesthesiaDoctor() {
        return anesthesiaDoctor;
    }

    public void setAnesthesiaDoctor(String anesthesiaDoctor) {
        this.anesthesiaDoctor = anesthesiaDoctor;
    }

    public String getAnesthesiaMethod() {
        return anesthesiaMethod;
    }

    public void setAnesthesiaMethod(String anesthesiaMethod) {
        this.anesthesiaMethod = anesthesiaMethod;
    }

    public String getReqDatetime() {
        return reqDatetime;
    }

    public void setReqDatetime(String reqDatetime) {
        this.reqDatetime = reqDatetime;
    }

    public String getNotesonOperation() {
        return notesonOperation;
    }

    public void setNotesonOperation(String notesonOperation) {
        this.notesonOperation = notesonOperation;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getExamineMind() {
        return examineMind;
    }

    public void setExamineMind(String examineMind) {
        this.examineMind = examineMind;
    }

    public String getExamineOperator() {
        return examineOperator;
    }

    public void setExamineOperator(String examineOperator) {
        this.examineOperator = examineOperator;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getReqDept() {
        return reqDept;
    }

    public void setReqDept(String reqDept) {
        this.reqDept = reqDept;
    }

    public String getOperatingRoomNo() {
        return operatingRoomNo;
    }

    public void setOperatingRoomNo(String operatingRoomNo) {
        this.operatingRoomNo = operatingRoomNo;
    }

    public String getOperatingName() {
        return operatingName;
    }

    public void setOperatingName(String operatingName) {
        this.operatingName = operatingName;
    }

    public String getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(String confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getArrangeDatetime() {
        return arrangeDatetime;
    }

    public void setArrangeDatetime(String arrangeDatetime) {
        this.arrangeDatetime = arrangeDatetime;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getSslb() {
        return sslb;
    }

    public void setSslb(String sslb) {
        this.sslb = sslb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRysj() {
        return rysj;
    }

    public void setRysj(String rysj) {
        this.rysj = rysj;
    }

}