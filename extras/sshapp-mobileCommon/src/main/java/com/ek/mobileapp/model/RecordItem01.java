package com.ek.mobileapp.model;

import java.io.Serializable;

//压疮记录单
public class RecordItem01 implements Serializable {

    private static final long serialVersionUID = -767079371648548110L;

    private Long id;
    private Long userId;//用户id
    private String userName;//用户姓名
    private String patientId;//住院号
    private String visitId;
    private String addDate;//保存日期
    private String state;//标记是否进行过保存,Y则提交给his
    //
    private String r_from_id;
    private String r_from;//来源
    private String r_datetime;//发生时间
    private String r_part_id;//部位id
    private String r_part;//部位
    private String r_size_id;//大小
    private String r_size;//
    private String r_level_id;//分期
    private String r_level;//
    private String r_show_id;//外观
    private String r_show;//
    private String r_exudate_id;//渗液
    private String r_exudate;
    private String r_treat_id;//治疗
    private String r_treat;
    private String r_nurse_id;//护理
    private String r_nurse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getR_from_id() {
        return r_from_id;
    }

    public void setR_from_id(String r_from_id) {
        this.r_from_id = r_from_id;
    }

    public String getR_from() {
        return r_from;
    }

    public void setR_from(String r_from) {
        this.r_from = r_from;
    }

    public String getR_datetime() {
        return r_datetime;
    }

    public void setR_datetime(String r_datetime) {
        this.r_datetime = r_datetime;
    }

    public String getR_part_id() {
        return r_part_id;
    }

    public void setR_part_id(String r_part_id) {
        this.r_part_id = r_part_id;
    }

    public String getR_part() {
        return r_part;
    }

    public void setR_part(String r_part) {
        this.r_part = r_part;
    }

    public String getR_size_id() {
        return r_size_id;
    }

    public void setR_size_id(String r_size_id) {
        this.r_size_id = r_size_id;
    }

    public String getR_size() {
        return r_size;
    }

    public void setR_size(String r_size) {
        this.r_size = r_size;
    }

    public String getR_level_id() {
        return r_level_id;
    }

    public void setR_level_id(String r_level_id) {
        this.r_level_id = r_level_id;
    }

    public String getR_level() {
        return r_level;
    }

    public void setR_level(String r_level) {
        this.r_level = r_level;
    }

    public String getR_show_id() {
        return r_show_id;
    }

    public void setR_show_id(String r_show_id) {
        this.r_show_id = r_show_id;
    }

    public String getR_show() {
        return r_show;
    }

    public void setR_show(String r_show) {
        this.r_show = r_show;
    }

    public String getR_exudate_id() {
        return r_exudate_id;
    }

    public void setR_exudate_id(String r_exudate_id) {
        this.r_exudate_id = r_exudate_id;
    }

    public String getR_exudate() {
        return r_exudate;
    }

    public void setR_exudate(String r_exudate) {
        this.r_exudate = r_exudate;
    }

    public String getR_treat_id() {
        return r_treat_id;
    }

    public void setR_treat_id(String r_treat_id) {
        this.r_treat_id = r_treat_id;
    }

    public String getR_treat() {
        return r_treat;
    }

    public void setR_treat(String r_treat) {
        this.r_treat = r_treat;
    }

    public String getR_nurse_id() {
        return r_nurse_id;
    }

    public void setR_nurse_id(String r_nurse_id) {
        this.r_nurse_id = r_nurse_id;
    }

    public String getR_nurse() {
        return r_nurse;
    }

    public void setR_nurse(String r_nurse) {
        this.r_nurse = r_nurse;
    }

}