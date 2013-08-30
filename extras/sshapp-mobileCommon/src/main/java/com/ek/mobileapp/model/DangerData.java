package com.ek.mobileapp.model;

//危急值
public class DangerData {

    private Long id;
    private String hisId;//his记录id,用于反写
    private String patientId;//住院号
    private String visitId;//就诊次数
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String bedNo;//床位号
    private String deptCode;//所在病区代码
    private String deptName;//所在病区
    private String itemName;//检验项目
    private String itemSpec;//项目规格
    private String value1;//结果值
    private String value2;//正常值
    private String checkBy;//检验人
    private String checkDate;//检验时间
    private String receiveWho;//接收人
    private String receiveWhoCode;//接收人代码
    private String receiveDeptName;//接收科室
    private String receiveDeptCode;//接收科室代码
    private String readTime;//阅读时间
    private String readBy;//阅读人
    private String readByCode;//阅读人代码
    private int state;//1未阅读,2已阅读未提交,3已提交

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getHisId() {
        return hisId;
    }

    public void setHisId(String hisId) {
        this.hisId = hisId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getReceiveWho() {
        return receiveWho;
    }

    public void setReceiveWho(String receiveWho) {
        this.receiveWho = receiveWho;
    }

    public String getReceiveWhoCode() {
        return receiveWhoCode;
    }

    public void setReceiveWhoCode(String receiveWhoCode) {
        this.receiveWhoCode = receiveWhoCode;
    }

    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    public void setReceiveDeptName(String receiveDeptName) {
        this.receiveDeptName = receiveDeptName;
    }

    public String getReceiveDeptCode() {
        return receiveDeptCode;
    }

    public void setReceiveDeptCode(String receiveDeptCode) {
        this.receiveDeptCode = receiveDeptCode;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getReadBy() {
        return readBy;
    }

    public void setReadBy(String readBy) {
        this.readBy = readBy;
    }

    public String getReadByCode() {
        return readByCode;
    }

    public void setReadByCode(String readByCode) {
        this.readByCode = readByCode;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

}