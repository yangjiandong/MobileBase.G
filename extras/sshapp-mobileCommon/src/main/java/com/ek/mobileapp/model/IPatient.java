package com.ek.mobileapp.model;

public class IPatient {

    private Long id;
    private Long userId;
    private String patientId;//住院号
    private String yymc;//医院名称
    private String wardName;//病区名称
    private String kb;//科别
    private String deptName;//科室名称
    private String bedNo;//床号
    private String patientName;//病人姓名
    private String sex;//性别
    private String age;//年龄
    private String zy;//职业
    private String hy;//婚姻
    private String doctorName;//床位医生
    private String jzfs;//结账方式
    private String zyts;//住院天数
    private String yjj;//预交金
    private String zfy;//总费用
    private String zyzd;//主要诊断
    private String lxdh;//联系电话
    private String txdz;//通讯地址
    private String rysj;//入院时间
    private String sssj;//手术时间
    private String ssdj;//手术等级
    private String ssmc;//手术名称
    private String dj;//等级
    private String blzy;//病历摘要
    private String mdyq;//目的要求
    private String hzys;//会诊医师
    private String hzks;//会诊科室
    private String hzyy;//会诊医院
    private String sqys;//申请医师
    private String sqsj;//申请时间
    private String zxks;//转向科室
    private String zxch;//转向床号
    private String zksj;//转科时间
    private String jysqdh;//检验申请单号
    private String bbmc;//标本名称
    private String sjsj;//送检时间
    private String byjdm;//病原菌代码
    private String byjmc;//病原菌名称
    private String sksj;//审核时间
    private String skr;//审核人
    private String visitId;
    private String condition;
    private String nurseClass;
    private String birth;//出生日期
    private String yblb;//医保类别
    private String jzsj;//接诊时间
    private String rytj;//入院途径
    private String rybq;//入院病情
    private String indept;//入院科室
    private String ryzd;//入院诊断(也为临床诊断)
    private String twozd;//二次诊断
    private String outdept;//出院科室
    private String cysj;//出院时间
    private String gms;//过敏史
    private String blxx;//病理信息

    private String zz;//症状
    private String tz;//体征

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getYymc() {
        return yymc;
    }

    public void setYymc(String yymc) {
        this.yymc = yymc;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getKb() {
        return kb;
    }

    public void setKb(String kb) {
        this.kb = kb;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getJzfs() {
        return jzfs;
    }

    public void setJzfs(String jzfs) {
        this.jzfs = jzfs;
    }

    public String getZyts() {
        return zyts;
    }

    public void setZyts(String zyts) {
        this.zyts = zyts;
    }

    public String getYjj() {
        return yjj;
    }

    public void setYjj(String yjj) {
        this.yjj = yjj;
    }

    public String getZfy() {
        return zfy;
    }

    public void setZfy(String zfy) {
        this.zfy = zfy;
    }

    public String getZyzd() {
        return zyzd;
    }

    public void setZyzd(String zyzd) {
        this.zyzd = zyzd;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getRysj() {
        return rysj;
    }

    public void setRysj(String rysj) {
        this.rysj = rysj;
    }

    public String getSssj() {
        return sssj;
    }

    public void setSssj(String sssj) {
        this.sssj = sssj;
    }

    public String getSsdj() {
        return ssdj;
    }

    public void setSsdj(String ssdj) {
        this.ssdj = ssdj;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getBlzy() {
        return blzy;
    }

    public void setBlzy(String blzy) {
        this.blzy = blzy;
    }

    public String getMdyq() {
        return mdyq;
    }

    public void setMdyq(String mdyq) {
        this.mdyq = mdyq;
    }

    public String getHzys() {
        return hzys;
    }

    public void setHzys(String hzys) {
        this.hzys = hzys;
    }

    public String getHzks() {
        return hzks;
    }

    public void setHzks(String hzks) {
        this.hzks = hzks;
    }

    public String getHzyy() {
        return hzyy;
    }

    public void setHzyy(String hzyy) {
        this.hzyy = hzyy;
    }

    public String getSqys() {
        return sqys;
    }

    public void setSqys(String sqys) {
        this.sqys = sqys;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getZxks() {
        return zxks;
    }

    public void setZxks(String zxks) {
        this.zxks = zxks;
    }

    public String getZxch() {
        return zxch;
    }

    public void setZxch(String zxch) {
        this.zxch = zxch;
    }

    public String getZksj() {
        return zksj;
    }

    public void setZksj(String zksj) {
        this.zksj = zksj;
    }

    public String getJysqdh() {
        return jysqdh;
    }

    public void setJysqdh(String jysqdh) {
        this.jysqdh = jysqdh;
    }

    public String getBbmc() {
        return bbmc;
    }

    public void setBbmc(String bbmc) {
        this.bbmc = bbmc;
    }

    public String getSjsj() {
        return sjsj;
    }

    public void setSjsj(String sjsj) {
        this.sjsj = sjsj;
    }

    public String getByjdm() {
        return byjdm;
    }

    public void setByjdm(String byjdm) {
        this.byjdm = byjdm;
    }

    public String getByjmc() {
        return byjmc;
    }

    public void setByjmc(String byjmc) {
        this.byjmc = byjmc;
    }

    public String getSksj() {
        return sksj;
    }

    public void setSksj(String sksj) {
        this.sksj = sksj;
    }

    public String getSkr() {
        return skr;
    }

    public void setSkr(String skr) {
        this.skr = skr;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNurseClass() {
        return nurseClass;
    }

    public void setNurseClass(String nurseClass) {
        this.nurseClass = nurseClass;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getYblb() {
        return yblb;
    }

    public void setYblb(String yblb) {
        this.yblb = yblb;
    }

    public String getJzsj() {
        return jzsj;
    }

    public void setJzsj(String jzsj) {
        this.jzsj = jzsj;
    }

    public String getRytj() {
        return rytj;
    }

    public void setRytj(String rytj) {
        this.rytj = rytj;
    }

    public String getRybq() {
        return rybq;
    }

    public void setRybq(String rybq) {
        this.rybq = rybq;
    }

    public String getIndept() {
        return indept;
    }

    public void setIndept(String indept) {
        this.indept = indept;
    }

    public String getRyzd() {
        return ryzd;
    }

    public void setRyzd(String ryzd) {
        this.ryzd = ryzd;
    }

    public String getTwozd() {
        return twozd;
    }

    public void setTwozd(String twozd) {
        this.twozd = twozd;
    }

    public String getOutdept() {
        return outdept;
    }

    public void setOutdept(String outdept) {
        this.outdept = outdept;
    }

    public String getCysj() {
        return cysj;
    }

    public void setCysj(String cysj) {
        this.cysj = cysj;
    }

    public String getGms() {
        return gms;
    }

    public void setGms(String gms) {
        this.gms = gms;
    }

    public String getBlxx() {
        return blxx;
    }

    public void setBlxx(String blxx) {
        this.blxx = blxx;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }
}