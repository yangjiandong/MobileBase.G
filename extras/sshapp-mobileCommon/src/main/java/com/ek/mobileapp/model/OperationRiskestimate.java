package com.ek.mobileapp.model;

//手术风险评估
public class OperationRiskestimate {

    private String patientId;//住院号
    private String visitId;
    private String name;
    private String sex;
    private String operateDate;//手术日期
    private String deptCode;//部门代码
    private String deptName;//部门名称
    private String operateName;//手术名称
    private String scheduleId;//手术安排日期
    private String doctor;//医生签名
    private String doctorId;//医生登登录名
    private String doctorDate;//医生签名时间
    private String anesthetist;//麻醉医生签名
    private String anesthetistId;//麻醉医生签名登录名
    private String anesthetistDate;//麻醉医生签名时间
    private String nurse;//护士签名
    private String nurseId;//护士签名登录名
    private String nurseDate;//护士签名时间
    private String doctor2;//随访医生签名
    private String doctor2Id;//随访医生登录名
    private String doctor2Date;//随访医生签名时间

    private String operatorName;//操作员
    private String operatorDate;//操作日期
    private String status;//状态

    private String cutType;//切口清洁度-切口类别
    private String cutDegreee;//切口清洁度-分数

    private String anesthesiaClass;//麻醉分级-麻醉等级
    private String asaDegree;//麻醉分级-分数
    private String operateClassqc;//麻醉分级-浅层
    private String operateClasssb;//麻醉分级-深部
    private String operateClassqg;//麻醉分级-器官
    private String operateClassqx;//麻醉分级-腔隙

    private String operateContiue;//持续时间-类别
    private String operateContiuedegree;//持续时间-分数
    private String cutJjyh;//持续时间-甲级愈合
    private String cutQkgrqc;//持续时间-浅层感染
    private String cutQkgrsc;//持续时间-深层感染
    private String nnisDegree;//持续时间-分数
    private String emergency;//持续时间-是否急诊手术

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
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

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorDate() {
        return doctorDate;
    }

    public void setDoctorDate(String doctorDate) {
        this.doctorDate = doctorDate;
    }

    public String getAnesthetist() {
        return anesthetist;
    }

    public void setAnesthetist(String anesthetist) {
        this.anesthetist = anesthetist;
    }

    public String getAnesthetistId() {
        return anesthetistId;
    }

    public void setAnesthetistId(String anesthetistId) {
        this.anesthetistId = anesthetistId;
    }

    public String getAnesthetistDate() {
        return anesthetistDate;
    }

    public void setAnesthetistDate(String anesthetistDate) {
        this.anesthetistDate = anesthetistDate;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseDate() {
        return nurseDate;
    }

    public void setNurseDate(String nurseDate) {
        this.nurseDate = nurseDate;
    }

    public String getDoctor2() {
        return doctor2;
    }

    public void setDoctor2(String doctor2) {
        this.doctor2 = doctor2;
    }

    public String getDoctor2Id() {
        return doctor2Id;
    }

    public void setDoctor2Id(String doctor2Id) {
        this.doctor2Id = doctor2Id;
    }

    public String getDoctor2Date() {
        return doctor2Date;
    }

    public void setDoctor2Date(String doctor2Date) {
        this.doctor2Date = doctor2Date;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(String operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCutType() {
        return cutType;
    }

    public void setCutType(String cutType) {
        this.cutType = cutType;
    }

    public String getCutDegreee() {
        return cutDegreee;
    }

    public void setCutDegreee(String cutDegreee) {
        this.cutDegreee = cutDegreee;
    }

    public String getAnesthesiaClass() {
        return anesthesiaClass;
    }

    public void setAnesthesiaClass(String anesthesiaClass) {
        this.anesthesiaClass = anesthesiaClass;
    }

    public String getAsaDegree() {
        return asaDegree;
    }

    public void setAsaDegree(String asaDegree) {
        this.asaDegree = asaDegree;
    }

    public String getOperateClassqc() {
        return operateClassqc;
    }

    public void setOperateClassqc(String operateClassqc) {
        this.operateClassqc = operateClassqc;
    }

    public String getOperateClasssb() {
        return operateClasssb;
    }

    public void setOperateClasssb(String operateClasssb) {
        this.operateClasssb = operateClasssb;
    }

    public String getOperateClassqg() {
        return operateClassqg;
    }

    public void setOperateClassqg(String operateClassqg) {
        this.operateClassqg = operateClassqg;
    }

    public String getOperateClassqx() {
        return operateClassqx;
    }

    public void setOperateClassqx(String operateClassqx) {
        this.operateClassqx = operateClassqx;
    }

    public String getOperateContiue() {
        return operateContiue;
    }

    public void setOperateContiue(String operateContiue) {
        this.operateContiue = operateContiue;
    }

    public String getOperateContiuedegree() {
        return operateContiuedegree;
    }

    public void setOperateContiuedegree(String operateContiuedegree) {
        this.operateContiuedegree = operateContiuedegree;
    }

    public String getCutJjyh() {
        return cutJjyh;
    }

    public void setCutJjyh(String cutJjyh) {
        this.cutJjyh = cutJjyh;
    }

    public String getCutQkgrqc() {
        return cutQkgrqc;
    }

    public void setCutQkgrqc(String cutQkgrqc) {
        this.cutQkgrqc = cutQkgrqc;
    }

    public String getCutQkgrsc() {
        return cutQkgrsc;
    }

    public void setCutQkgrsc(String cutQkgrsc) {
        this.cutQkgrsc = cutQkgrsc;
    }

    public String getNnisDegree() {
        return nnisDegree;
    }

    public void setNnisDegree(String nnisDegree) {
        this.nnisDegree = nnisDegree;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

}