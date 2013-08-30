package com.ek.mobileapp.model;

//手术审批
public class OperationApprovalData {
    private Long id;
    private String appNo;//his申请单编号
    private String appDate;//申请时间
    private String appWho;//申请人
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String bedNo;//床号
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String marriage;//婚姻
    private String occupation;//职业
    private String inTime;//入院时间
    private String wardName;//所在病区
    private String deptName;//所在科室
    private String diagnosis;//术前诊断
    private String condition;//病历摘要
    private String advice;//术前讨论意见
    private String operationName;//手术名称
    private String narcosisMethod;//麻醉方法
    private String hasBlood;//备血
    private String operationType;//手术种类
    private String operationByWho;//手术者
    private String narcosisByWho;//麻醉者
    private String mayContent;//术中可能发生的意外及处理
    private String result;//审批结果,0未审批,1同意,2不同意
    private String note;//备注
    private String dealDate;//审批时间
    private String dealWho;//审批人
    private String dealWhoCode;//审批人代码
    private int state;//1未审核,2已审核未提交,3已提交
    private String isSend;//是否已发短信

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppWho() {
        return appWho;
    }

    public void setAppWho(String appWho) {
        this.appWho = appWho;
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

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getNarcosisMethod() {
        return narcosisMethod;
    }

    public void setNarcosisMethod(String narcosisMethod) {
        this.narcosisMethod = narcosisMethod;
    }

    public String getHasBlood() {
        return hasBlood;
    }

    public void setHasBlood(String hasBlood) {
        this.hasBlood = hasBlood;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationByWho() {
        return operationByWho;
    }

    public void setOperationByWho(String operationByWho) {
        this.operationByWho = operationByWho;
    }

    public String getNarcosisByWho() {
        return narcosisByWho;
    }

    public void setNarcosisByWho(String narcosisByWho) {
        this.narcosisByWho = narcosisByWho;
    }

    public String getMayContent() {
        return mayContent;
    }

    public void setMayContent(String mayContent) {
        this.mayContent = mayContent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getDealWho() {
        return dealWho;
    }

    public void setDealWho(String dealWho) {
        this.dealWho = dealWho;
    }

    public String getDealWhoCode() {
        return dealWhoCode;
    }

    public void setDealWhoCode(String dealWhoCode) {
        this.dealWhoCode = dealWhoCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }

}