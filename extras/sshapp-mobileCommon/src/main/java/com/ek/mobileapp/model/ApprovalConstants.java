package com.ek.mobileapp.model;

public class ApprovalConstants {

    public static final String MODULE_DRUG = "01";
    public static final String MODULE_OPER = "02";
    public static final String MODULE_DANGER = "03";
    public static final String MODULE_BLOOD = "04";
    public static final String MODULE_INFECTION = "05";

    public static final Integer APPROVAL_GET = 1;//未保存
    public static final Integer APPROVAL_SAVE = 2;//已保存未提交
    public static final Integer APPROVAL_COMMIT = 3;//已提交

    public static final Integer MESSAGE_UNREAD = 1;//未阅读
    public static final Integer MESSAGE_READ = 2;//已阅读
    public static final Integer MESSAGE_DEAL = 3;//已处理

    public static final String MESSAGE_SEND_NO = "N";
    public static final String MESSAGE_SEND_YES = "Y";

    public static final Integer MESSAGE_RECEIVER_DEPT = 1;
    public static final Integer MESSAGE_RECEIVER_EMP = 2;

    public static final Integer MESSAGE_TYPE_1 = 1;//
    public static final Integer MESSAGE_TYPE_2 = 2;//
    public static final Integer MESSAGE_TYPE_3 = 3;//检验报告,危急值

    public static final String MESSAGE_RECEIVERWHO_QUERY = "01";//全院概况数据接收人
    public static final String MESSAGE_RECEIVERWHO_OTHER = "02";//其他
    public static final String MESSAGE_RECEIVERWHO_BLOOD = "03";//用血审批数据接收人
    public static final String MESSAGE_RECEIVERWHO_OPERATION = "04";//重大手术数据接收人
}

