package com.ek.mobileapp.model;

//检查报告
public class MedicalReportDetailJC {
    private String checkClass;//检查类别
    private String subClass;//子类
    private String checkCode;//检查号
    private String applicant;//申请人
    private String applyTime;//申请时间
    private String reporter;//报告人
    private String reportTime;//报告时间
    private String auditor;//审核人
    private String auditTime;//审核时间
    private String checkParm;//检查参数
    private String checkSeen;//检查所见
    private String impression;//印象
    private String suggestion;//建议

    public String getCheckClass() {
        return checkClass;
    }

    public void setCheckClass(String checkClass) {
        this.checkClass = checkClass;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getCheckParm() {
        return checkParm;
    }

    public void setCheckParm(String checkParm) {
        this.checkParm = checkParm;
    }

    public String getCheckSeen() {
        return checkSeen;
    }

    public void setCheckSeen(String checkSeen) {
        this.checkSeen = checkSeen;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}