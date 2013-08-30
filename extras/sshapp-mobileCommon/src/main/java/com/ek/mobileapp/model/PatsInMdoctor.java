package com.ek.mobileapp.model;

import java.io.Serializable;

public class PatsInMdoctor implements Serializable {
    private static final long serialVersionUID = -2565491631419791424L;
    private String patientId;//住院号
    private String patName;//病人名字
    private String sex;//病人名字
    private String wardCode;//病区
    private String deptCode;//科室
    private Long bedNo;//座位号
    private String admWardDateTime;//进来时间
    private String oldBedNo;//结束后的座位号
    private String leaveDateTime;//离开时间
    private Long age;//年龄
    private Long unit;//单位

    public String getPatientId() {
        return patientId;
    }
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public String getPatName() {
        return patName;
    }
    public void setPatName(String patName) {
        this.patName = patName;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getWardCode() {
        return wardCode;
    }
    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    public Long getBedNo() {
        return bedNo;
    }
    public void setBedNo(Long bedNo) {
        this.bedNo = bedNo;
    }
    public String getAdmWardDateTime() {
        return admWardDateTime;
    }
    public void setAdmWardDateTime(String admWardDateTime) {
        this.admWardDateTime = admWardDateTime;
    }
    public String getOldBedNo() {
        return oldBedNo;
    }
    public void setOldBedNo(String oldBedNo) {
        this.oldBedNo = oldBedNo;
    }
    public String getLeaveDateTime() {
        return leaveDateTime;
    }
    public void setLeaveDateTime(String leaveDateTime) {
        this.leaveDateTime = leaveDateTime;
    }
    public Long getAge() {
        return age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public Long getUnit() {
        return unit;
    }
    public void setUnit(Long unit) {
        this.unit = unit;
    }

}
