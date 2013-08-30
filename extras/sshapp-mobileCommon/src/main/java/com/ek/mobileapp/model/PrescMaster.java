package com.ek.mobileapp.model;

//处方主单
public class PrescMaster {

    private String prescNo;//处方号
    private String patientId;//住院号
    private String visitId;//就诊次数

    private int prescType;//处方类型
    private int repetition;//贴数
    private String performedBy;//执行药房代码
    private String performedNameBy;
    private String orderedBy;//开方科室
    private String prescriber;//医师
    private String enteredBy;//录入人
    private String enteredDateTime;//录入时间

    private int dispenseIndicator;//发药标记
    private String prescOrigin;
    private int outpFlag;//领药类型,1门诊领药,2出院带药
    private String outpFlagName;
    private int confirmFlag;//确认标记
    private String confirmNurse;//确认护士

    private int confirmAcct;//是否记账

    private String empNO;//开立医生工号
    private String deptCode;

    public String getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(String prescNo) {
        this.prescNo = prescNo;
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

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getPerformedNameBy() {
        return performedNameBy;
    }

    public void setPerformedNameBy(String performedNameBy) {
        this.performedNameBy = performedNameBy;
    }

    public String getOrderedBy() {
        return orderedBy;
    }

    public void setOrderedBy(String orderedBy) {
        this.orderedBy = orderedBy;
    }

    public String getPrescriber() {
        return prescriber;
    }

    public void setPrescriber(String prescriber) {
        this.prescriber = prescriber;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getEnteredDateTime() {
        return enteredDateTime;
    }

    public void setEnteredDateTime(String enteredDateTime) {
        this.enteredDateTime = enteredDateTime;
    }

    public String getPrescOrigin() {
        return prescOrigin;
    }

    public void setPrescOrigin(String prescOrigin) {
        this.prescOrigin = prescOrigin;
    }

    public String getEmpNO() {
        return empNO;
    }

    public void setEmpNO(String empNO) {
        this.empNO = empNO;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getConfirmNurse() {
        return confirmNurse;
    }

    public void setConfirmNurse(String confirmNurse) {
        this.confirmNurse = confirmNurse;
    }

    public int getPrescType() {
        return prescType;
    }

    public void setPrescType(int prescType) {
        this.prescType = prescType;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getDispenseIndicator() {
        return dispenseIndicator;
    }

    public void setDispenseIndicator(int dispenseIndicator) {
        this.dispenseIndicator = dispenseIndicator;
    }

    public int getOutpFlag() {
        return outpFlag;
    }

    public void setOutpFlag(int outpFlag) {
        this.outpFlag = outpFlag;
    }

    public String getOutpFlagName() {
        return outpFlagName;
    }

    public void setOutpFlagName(String outpFlagName) {
        this.outpFlagName = outpFlagName;
    }

    public int getConfirmFlag() {
        return confirmFlag;
    }

    public void setConfirmFlag(int confirmFlag) {
        this.confirmFlag = confirmFlag;
    }

    public int getConfirmAcct() {
        return confirmAcct;
    }

    public void setConfirmAcct(int confirmAcct) {
        this.confirmAcct = confirmAcct;
    }

}