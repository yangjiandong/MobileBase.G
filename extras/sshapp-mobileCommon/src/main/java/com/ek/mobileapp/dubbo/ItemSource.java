package com.ek.mobileapp.dubbo;

import java.io.Serializable;

//自动执行任务的数据源
public class ItemSource implements Serializable {
    private static final long serialVersionUID = 8658702729921362894L;
    private Long id;
    private Long itemId;
    private String itemName;
    private String spName;
    private String typeCode;//标记菜单来源,01:全院概况
    private Long cronId;
    private String cron;
    private String expression;
    private Long dbId;
    private String isAuto;
    private String dbResource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Long getCronId() {
        return cronId;
    }

    public void setCronId(Long cronId) {
        this.cronId = cronId;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(String isAuto) {
        this.isAuto = isAuto;
    }

    public String getDbResource() {
        return dbResource;
    }

    public void setDbResource(String dbResource) {
        this.dbResource = dbResource;
    }

}