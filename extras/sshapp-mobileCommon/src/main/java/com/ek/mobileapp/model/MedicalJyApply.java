package com.ek.mobileapp.model;

//检验申请单
public class MedicalJyApply {
    private String testNo;//检查申请单号
    private String patientId;
    private String visitId;
    private String name;
    private String namePhonetic;//名字缩写
    private String chargeType;//费用类别
    private String age;
    private String sex;
    private String bedNo;
    private String wardDept;

    private String resultStatus;//状态
    private String specimen;//标本
    private String spcmNotes;//标本说明
    private String relevantclinicDiag;//临床诊断
    private String applyDate;//申请时间
    private String applyDept;//申请科室
    private String applyDoctor;//申请医生
    private String empNo;//申请医生
    private String performedBy;//执行科室
    private String priorityIndicator;//优先标志0-普通 1-紧急
    private String orderNo;
    private String orderId;

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePhonetic() {
        return namePhonetic;
    }

    public void setNamePhonetic(String namePhonetic) {
        this.namePhonetic = namePhonetic;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getWardDept() {
        return wardDept;
    }

    public void setWardDept(String wardDept) {
        this.wardDept = wardDept;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getSpcmNotes() {
        return spcmNotes;
    }

    public void setSpcmNotes(String spcmNotes) {
        this.spcmNotes = spcmNotes;
    }

    public String getRelevantclinicDiag() {
        return relevantclinicDiag;
    }

    public void setRelevantclinicDiag(String relevantclinicDiag) {
        this.relevantclinicDiag = relevantclinicDiag;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyDept() {
        return applyDept;
    }

    public void setApplyDept(String applyDept) {
        this.applyDept = applyDept;
    }

    public String getApplyDoctor() {
        return applyDoctor;
    }

    public void setApplyDoctor(String applyDoctor) {
        this.applyDoctor = applyDoctor;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getPriorityIndicator() {
        return priorityIndicator;
    }

    public void setPriorityIndicator(String priorityIndicator) {
        this.priorityIndicator = priorityIndicator;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}