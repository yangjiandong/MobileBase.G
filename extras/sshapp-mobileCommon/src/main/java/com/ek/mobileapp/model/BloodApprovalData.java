package com.ek.mobileapp.model;

//用血审批
public class BloodApprovalData {

    private Long id;
    private String appNo;//申请单号
    private String transDate;//输血日期
    private String patientId;//病人ID
    private String visitId;//就诊次数
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String wardName;//病区
    private String deptName;//科室
    private String bedNo;//床号
    private String diagnose;//诊断
    private String aim;//输血目的
    private String history;//继往输血史
    private String hematin;//血红蛋白
    private String hct;//血球压积
    private String platelet;//血小板
    private String patBloodGroup;//受血者血型
    private String element1;//输血成分1
    private String element2;//输血成分2
    private String element3;//输血成分3
    private String element4;//输血成分4
    private String capacity1;//定用血量1（含单位保存）
    private String capacity2;//定用血量2
    private String capacity3;//定用血量3
    private String capacity4;//定用血量4
    private String address;//受血者属地
    private String pregnant;//孕
    private String baby;//产
    private String fastSlow;//是否急诊  1预定, 2急诊
    private int state;
    private String bloodDocDis;//输血科医师会诊
    private String appWho;//申请人（申请医师签字）
    private String appDate;//申请日期
    private String appDeptLeader;//申请科室主任签字
    private String note;
    private String dealWho;//审批人（医务处签字）
    private String dealDate;//审批日期（批准日期）
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

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
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

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHematin() {
        return hematin;
    }

    public void setHematin(String hematin) {
        this.hematin = hematin;
    }

    public String getHct() {
        return hct;
    }

    public void setHct(String hct) {
        this.hct = hct;
    }

    public String getPlatelet() {
        return platelet;
    }

    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }

    public String getPatBloodGroup() {
        return patBloodGroup;
    }

    public void setPatBloodGroup(String patBloodGroup) {
        this.patBloodGroup = patBloodGroup;
    }

    public String getElement1() {
        return element1;
    }

    public void setElement1(String element1) {
        this.element1 = element1;
    }

    public String getElement2() {
        return element2;
    }

    public void setElement2(String element2) {
        this.element2 = element2;
    }

    public String getElement3() {
        return element3;
    }

    public void setElement3(String element3) {
        this.element3 = element3;
    }

    public String getElement4() {
        return element4;
    }

    public void setElement4(String element4) {
        this.element4 = element4;
    }

    public String getCapacity1() {
        return capacity1;
    }

    public void setCapacity1(String capacity1) {
        this.capacity1 = capacity1;
    }

    public String getCapacity2() {
        return capacity2;
    }

    public void setCapacity2(String capacity2) {
        this.capacity2 = capacity2;
    }

    public String getCapacity3() {
        return capacity3;
    }

    public void setCapacity3(String capacity3) {
        this.capacity3 = capacity3;
    }

    public String getCapacity4() {
        return capacity4;
    }

    public void setCapacity4(String capacity4) {
        this.capacity4 = capacity4;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPregnant() {
        return pregnant;
    }

    public void setPregnant(String pregnant) {
        this.pregnant = pregnant;
    }

    public String getBaby() {
        return baby;
    }

    public void setBaby(String baby) {
        this.baby = baby;
    }

    public String getFastSlow() {
        return fastSlow;
    }

    public void setFastSlow(String fastSlow) {
        this.fastSlow = fastSlow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getBloodDocDis() {
        return bloodDocDis;
    }

    public void setBloodDocDis(String bloodDocDis) {
        this.bloodDocDis = bloodDocDis;
    }

    public String getAppWho() {
        return appWho;
    }

    public void setAppWho(String appWho) {
        this.appWho = appWho;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppDeptLeader() {
        return appDeptLeader;
    }

    public void setAppDeptLeader(String appDeptLeader) {
        this.appDeptLeader = appDeptLeader;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDealWho() {
        return dealWho;
    }

    public void setDealWho(String dealWho) {
        this.dealWho = dealWho;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }
}