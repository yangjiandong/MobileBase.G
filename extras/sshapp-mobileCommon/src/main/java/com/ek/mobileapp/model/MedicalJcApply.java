package com.ek.mobileapp.model;

//检查申请单
public class MedicalJcApply {
    private String examNo;//检查申请单号
    private String patientId;
    private String visitId;
    private String name;
    private String localidClass;//检查号类别
    private String patientlocalId;//检查标识号
    private String birth;
    private String sex;
    private String chargeType;//费用类别
    private String bedNo;
    private String wardDept;

    private String resultStatus;//状态
    private String examClass;//检查类别
    private String examsubClass;//检查子类
    private String clinSymp;//临床症状
    private String physSign;//体征
    private String relevantDiag;//其他诊断
    private String clinDiag;//临床诊断
    private String examMode;//检查方式
    private String patientSource;//病人来源
    private String applyDate;//申请时间
    private String applyDept;//申请科室
    private String applyDoctor;//申请医生
    private String empNo;//申请医生
    private String performedBy;//执行科室
    private String priorityIndicator;//优先标志0-普通 1-紧急
    private String identity;//身份
    private String zipCode;//籍贯代码
    private String mailingAddress;//通信地址

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
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

    public String getLocalidClass() {
        return localidClass;
    }

    public void setLocalidClass(String localidClass) {
        this.localidClass = localidClass;
    }

    public String getPatientlocalId() {
        return patientlocalId;
    }

    public void setPatientlocalId(String patientlocalId) {
        this.patientlocalId = patientlocalId;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
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

    public String getExamClass() {
        return examClass;
    }

    public void setExamClass(String examClass) {
        this.examClass = examClass;
    }

    public String getExamsubClass() {
        return examsubClass;
    }

    public void setExamsubClass(String examsubClass) {
        this.examsubClass = examsubClass;
    }

    public String getClinSymp() {
        return clinSymp;
    }

    public void setClinSymp(String clinSymp) {
        this.clinSymp = clinSymp;
    }

    public String getPhysSign() {
        return physSign;
    }

    public void setPhysSign(String physSign) {
        this.physSign = physSign;
    }

    public String getRelevantDiag() {
        return relevantDiag;
    }

    public void setRelevantDiag(String relevantDiag) {
        this.relevantDiag = relevantDiag;
    }

    public String getClinDiag() {
        return clinDiag;
    }

    public void setClinDiag(String clinDiag) {
        this.clinDiag = clinDiag;
    }

    public String getExamMode() {
        return examMode;
    }

    public void setExamMode(String examMode) {
        this.examMode = examMode;
    }

    public String getPatientSource() {
        return patientSource;
    }

    public void setPatientSource(String patientSource) {
        this.patientSource = patientSource;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}