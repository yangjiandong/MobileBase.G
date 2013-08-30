package com.ek.mobileapp.model;

public class MobConstants {

    public static final String MOB_QUERY = "01";//全院概况
    public static final String MOB_VITALSIGN = "02";//生命体征
    public static final String MOB_DRUGCHECK = "03";//医嘱执行
    public static final String MOB_DRUGAPPR = "04";//用药审批
    public static final String MOB_OPERAPPR = "05";//手术审批
    public static final String MOB_DANGER = "06";//危急值
    public static final String MOB_VIP = "07";//重点病人
    public static final String MOB_BLOODAPPR = "12";//用血审批

    //移动护理
    //医嘱类别
    public static final String MOB_NURSE_DRUGCHECK = "2";//输液
    public static final String MOB_NURSE_DRUGCHECK3 = "3";//服药
    public static final String MOB_NURSE_DRUGCHECK2 = "1";//注射
    public static final String MOB_NURSE_DRUGCHECK4 = "4";//治疗单
    public static final String MOB_NURSE_DRUGCHECK5 = "5";//护理单
    public static final String MOB_NURSE_DRUGCHECK_MED = "MED";//检验
    public static final String MOB_NURSE_DRUGCHECK_BLOOD = "BLOOD";//输血
    public static final String MOB_NURSE_DRUGCHECK_CT = "CT";//检查

    public static final String MOB_VITALSIGN_MORE = "1";//生命体征测量:一天多次,按时间点
    public static final String MOB_VITALSIGN_ONE = "2";//生命体征测量:一天一次

    public static final String MOB_CHECKTYPE_ZHUSHE = "1";
    public static final String MOB_CHECKTYPE_SHUYE = "2";
    public static final String MOB_CHECKTYPE_KOUFU = "3";

    public static final String MOB_ACTIVE = "Y";
    public static final String MOB_INACTIVE = "N";

    //存储过程
    public static final String MOB_SPNAME_GET_PATIENT = "sp_mob_get_patient";
    public static final String MOB_SPNAME_GET_PATIENT_BY_BEDNO = "sp_mob_get_patient_by_bedno";
    public static final String MOB_SPNAME_GET_PATIENT_ALL = "sp_mob_get_patient_all";
    public static final String MOB_SPNAME_GET_VITALSIGN = "sp_mob_get_vitalsign";
    public static final String MOB_SPNAME_COMMIT_VITALSIGN = "sp_mob_commit_vitalsign";

    public static final String MOB_SPNAME_GET_DRUGCHECK = "sp_mob_drug_check";
    public static final String MOB_SPNAME_COMMIT_DRUGCHECK = "sp_mob_commit_drug_check";

    public static final String MOB_SPNAME_COMMIT_DRUGAPPROVAL = "sp_mob_commit_drug_approval";
    public static final String MOB_SPNAME_COMMIT_OPERAPPROVAL = "sp_mob_commit_oper_approval";

    public static final String MOB_VITALSIGN_STATE_UPDATE = "Y";
    public static final String MOB_VITALSIGN_STATE_QUERY = "N";

    public static final String MOB_WORKLOAD_TYPE_DRUGCHECK = "1";
    public static final String MOB_WORKLOAD_TYPE_OPERCHECK = "2";
    public static final String MOB_WORKLOAD_TYPE_ASSESSMONITOR = "3";//评估监测

    public static final String DBTYPE_MSSQL = "1";
    public static final String DBTYPE_ORACLE = "2";
    public static final String DBTYPE_CACHE = "3";

    public static final Integer MOB_APPROVAL_GET = 1;
    public static final Integer MOB_APPROVAL_SAVE = 2;
    public static final Integer MOB_APPROVAL_COMMIT = 3;

    public static final String FREQ_Q3D = "Q3D";
    public static final String FREQ_QOD = "QOD";

    public static final boolean short_order_use_select_time = true;

    public static final int ORDER_ZL = 1;//整理医嘱
    public static final int ORDER_CQ = 2;//长期医嘱
    public static final int ORDER_LS = 3;//临时医嘱

    public static final int ORDER_TYPE_LONG = 1;//长期医嘱
    public static final int ORDER_TYPE_SHORT = 0;//临时医嘱

    //
    public static final String SETUP_USE_BEDTABLE = "user_use_bedtable";

    public static final String MONITOR_RECORD = "01"; //监测记录,录入
    public static final String MONITOR_QUERY = "02"; //监测记录,查询

    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_SHUYE = "01";//输液
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_ZHUSHE = "02";//注射
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_GEIYAO = "03";//给药
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_SHUXUE = "04";//输血
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_CHUZHI = "05";//处置
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_JIANYAN = "06";//检验采集
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_JIANLIAO = "07";//检疗预约
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_HULI = "08";//护理
    public static final String MOB_NURSE_MOUDLE_DRUGCHECK_XUNHU = "09";//床边巡视
    public static final String MOB_NURSE_MOUDLE_HEALTHEDUCATION = "20";//健康宣教
    public static final String MOB_NURSE_MOUDLE_PRESSSORE_ASSESS = "24";//压疮评估
    public static final String MOB_NURSE_MOUDLE_PRESSSORE_RECORD = "25";//压疮记录

    public static final String MOB_NURSE_MOUDLE_OUT_SHUYE = "21";//门诊输液
    public static final String MOB_NURSE_MOUDLE_OUT_ZHUSHE = "22";//门诊注射
    public static final String MOB_NURSE_MOUDLE_OUT_PISHI = "23";//门诊皮试

    public static final String MOB_SYSTEM_NURSE = "nurse";//护士站
    public static final String MOB_SYSTEM_DOCTOR = "doctor";//医生站
    public static final String MOB_SYSTEM_QUERY = "query";//决策支持
    public static final String MOB_SYSTEM_INFUSION = "infusion";//移动输液
    public static final String MOB_SYSTEM_APPROVAL = "approval";//移动审批
    public static final String MOB_SYSTEM_ORDERING = "ordering";//移动点餐
    public static final String MOB_SYSTEM_TASK = "task";//任务提醒

    public static final String MOB_INFUSION_MESSAGE_SUCCESSFUL = "成功";
    public static final String MOB_INFUSION_MESSAGE_NULL = "数据为空";
    public static final String MOB_INFUSION_MESSAGE_FAILURE = "数据错误";

    public static final String MOB_NET_TYPE_WIFI = "1";
    public static final String MOB_NET_TYPE_3G = "2";
    public static final String MOB_NET_TYPE_BOTH = "3";
    public static final String MOB_NET_TYPE_ERROR = "0";

    //public static final int MOB_BARCODE_LENGTH_DRUG = 10;
    //public static final int MOB_BARCODE_LENGTH_PATIENT = 6;

    public static final String MOB_ORDERING = "01";
}
