package com.ek.mobileapp.model;

//检验报告
public class MedicalReportDetailJY {
    private String reportItemCode;//报告项目代码
    private String reportItemName;//报告项目名称
    private String result;//结果
    private String ExamException;//异常
    private String units;//单位
    private String normalValue;//正常值

    public String getReportItemCode() {
        return reportItemCode;
    }

    public void setReportItemCode(String reportItemCode) {
        this.reportItemCode = reportItemCode;
    }

    public String getReportItemName() {
        return reportItemName;
    }

    public void setReportItemName(String reportItemName) {
        this.reportItemName = reportItemName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExamException() {
        return ExamException;
    }

    public void setExamException(String examException) {
        ExamException = examException;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getNormalValue() {
        return normalValue;
    }

    public void setNormalValue(String normalValue) {
        this.normalValue = normalValue;
    }
}