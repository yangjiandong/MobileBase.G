package com.ek.mobileapp.model;

//输血信息
public class BloodInfo {

    private String appleNum;//申请单号
    private String inpNo;//病人住院标识号
    private String patientId;//住院号
    private String deptCode;//申请科室代码
    private String patientName;//姓名
    private String sex;
    private String birthday;
    private String feeType;//费别
    private String patSource;//病人来源
    private String bloodInuse;//用血方式
    private String bloodDiagnose;//诊断及输血适应症
    private String bloodTaboo;//输血反应及输血禁忌症
    private String hematin;//血色素
    private String platelet;//血小板
    private String leucocyte;//白血球
    private String patbloodGroup;//受血者血型A, B, AB, O
    private String rh;//Rh血型
    private String bloodSum;//输血总量
    private String applyDate;//申请填写时间
    private String gatherDate;//血库收到时间
    private String director;//主任
    private String physician;//主治
    private String mailingAddress;//家庭住址
    private String idNo;//身份证号
    private String serviceAgency;//工作单位
    private String fristBlood;//既往输血史
    private String pregnant;//孕
    private String baby;//产
    private String alt;//ALT
    private String hct;//HCT
    private String hbsag;//hbsag
    private String antiHcv;//anti_hcv
    private String antiHiv12;//anti_hiv12
    private String antiLues;//梅毒抗体
    private String bedNo;//床号
    private String bqCode;//病区代码
    private String empNo;//医生工号
    private String fastSlow;//用血方式1-急诊 2-普通 3-备血
    private String printFlag;//领血单打印标记
    private String lastprintDate;//领血单打印时间
    private String userId;//医生登录名
    private String visitId;//住院次数
    private String isfx;//是否发血标记
    private String orderId;//对应医嘱的编号
    public String getAppleNum() {
        return appleNum;
    }
    public void setAppleNum(String appleNum) {
        this.appleNum = appleNum;
    }
    public String getInpNo() {
        return inpNo;
    }
    public void setInpNo(String inpNo) {
        this.inpNo = inpNo;
    }
    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getFeeType() {
        return feeType;
    }
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    public String getPatSource() {
        return patSource;
    }
    public void setPatSource(String patSource) {
        this.patSource = patSource;
    }
    public String getBloodInuse() {
        return bloodInuse;
    }
    public void setBloodInuse(String bloodInuse) {
        this.bloodInuse = bloodInuse;
    }
    public String getBloodDiagnose() {
        return bloodDiagnose;
    }
    public void setBloodDiagnose(String bloodDiagnose) {
        this.bloodDiagnose = bloodDiagnose;
    }
    public String getBloodTaboo() {
        return bloodTaboo;
    }
    public void setBloodTaboo(String bloodTaboo) {
        this.bloodTaboo = bloodTaboo;
    }
    public String getHematin() {
        return hematin;
    }
    public void setHematin(String hematin) {
        this.hematin = hematin;
    }
    public String getPlatelet() {
        return platelet;
    }
    public void setPlatelet(String platelet) {
        this.platelet = platelet;
    }
    public String getLeucocyte() {
        return leucocyte;
    }
    public void setLeucocyte(String leucocyte) {
        this.leucocyte = leucocyte;
    }
    public String getPatbloodGroup() {
        return patbloodGroup;
    }
    public void setPatbloodGroup(String patbloodGroup) {
        this.patbloodGroup = patbloodGroup;
    }
    public String getRh() {
        return rh;
    }
    public void setRh(String rh) {
        this.rh = rh;
    }
    public String getBloodSum() {
        return bloodSum;
    }
    public void setBloodSum(String bloodSum) {
        this.bloodSum = bloodSum;
    }
    public String getApplyDate() {
        return applyDate;
    }
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    public String getGatherDate() {
        return gatherDate;
    }
    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getPhysician() {
        return physician;
    }
    public void setPhysician(String physician) {
        this.physician = physician;
    }
    public String getMailingAddress() {
        return mailingAddress;
    }
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
    public String getIdNo() {
        return idNo;
    }
    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
    public String getServiceAgency() {
        return serviceAgency;
    }
    public void setServiceAgency(String serviceAgency) {
        this.serviceAgency = serviceAgency;
    }
    public String getFristBlood() {
        return fristBlood;
    }
    public void setFristBlood(String fristBlood) {
        this.fristBlood = fristBlood;
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
    public String getAlt() {
        return alt;
    }
    public void setAlt(String alt) {
        this.alt = alt;
    }
    public String getHct() {
        return hct;
    }
    public void setHct(String hct) {
        this.hct = hct;
    }
    public String getHbsag() {
        return hbsag;
    }
    public void setHbsag(String hbsag) {
        this.hbsag = hbsag;
    }
    public String getAntiHcv() {
        return antiHcv;
    }
    public void setAntiHcv(String antiHcv) {
        this.antiHcv = antiHcv;
    }
    public String getAntiHiv12() {
        return antiHiv12;
    }
    public void setAntiHiv12(String antiHiv12) {
        this.antiHiv12 = antiHiv12;
    }
    public String getAntiLues() {
        return antiLues;
    }
    public void setAntiLues(String antiLues) {
        this.antiLues = antiLues;
    }
    public String getBedNo() {
        return bedNo;
    }
    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }
    public String getBqCode() {
        return bqCode;
    }
    public void setBqCode(String bqCode) {
        this.bqCode = bqCode;
    }
    public String getEmpNo() {
        return empNo;
    }
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    public String getFastSlow() {
        return fastSlow;
    }
    public void setFastSlow(String fastSlow) {
        this.fastSlow = fastSlow;
    }
    public String getPrintFlag() {
        return printFlag;
    }
    public void setPrintFlag(String printFlag) {
        this.printFlag = printFlag;
    }
    public String getLastprintDate() {
        return lastprintDate;
    }
    public void setLastprintDate(String lastprintDate) {
        this.lastprintDate = lastprintDate;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getVisitId() {
        return visitId;
    }
    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
    public String getIsfx() {
        return isfx;
    }
    public void setIsfx(String isfx) {
        this.isfx = isfx;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}