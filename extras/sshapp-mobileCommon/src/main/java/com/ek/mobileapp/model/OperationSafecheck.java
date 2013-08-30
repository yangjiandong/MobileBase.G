package com.ek.mobileapp.model;

//手术安全核查
public class OperationSafecheck {

    private String patientId;//住院号
    private String visitId;
    private String operateDate;//手术日期
    private String deptCode;//部门代码
    private String deptName;
    private String operateName;//手术名称
    private String scheduleId;//手术安排日期
    private String doctor;//医生名称
    private String anesthetist;//麻醉医生
    private String nurse;//护士
    private String operatorName;//操作员
    private String operatorDate;//操作日期
    private String status;//状态

    private String operatorBegin;//手术医生开始操作时间
    private String useridBegin;//手术医生开始操作ID
    private String operatordateBegin;//手术医生暂停操作时间
    private String operatorPause;//手术医生暂停操作时间
    private String useridPause;//手术医生暂停操作ID
    private String operatordatePause;//手术医生开始操作时间
    private String operatorEnd;//手术医生结束操作时间
    private String useridEnd;//手术医生结束操作ID
    private String operatordateEnd;//手术医生结束操作时间

    private String operatorBegin2;//麻醉医生开始操作时间
    private String useridBegin2;//麻醉医生开始操作ID
    private String operatordateBegin2;//麻醉医生暂停操作时间
    private String operatorPause2;//麻醉医生暂停操作时间
    private String useridPause2;//麻醉医生暂停操作ID
    private String operatordatePause2;//麻醉医生开始操作时间
    private String operatorEnd2;//麻醉医生结束操作时间
    private String useridEnd2;//麻醉医生结束操作ID
    private String operatordateEnd2;//麻醉医生结束操作时间

    private String operatorBegin3;//手术护士开始操作时间
    private String useridBegin3;//手术护士开始操作ID
    private String operatordateBegin3;//手术护士暂停操作时间
    private String operatorPause3;//手术护士暂停操作时间
    private String useridPause3;//手术护士暂停操作ID
    private String operatordatePause3;//手术护士开始操作时间
    private String operatorEnd3;//手术护士结束操作时间
    private String useridEnd3;//手术护士结束操作ID
    private String operatordateEnd3;//手术护士结束操作时间

    private String beginId;//开始-患者身份
    private String beginOperpos;//开始-手术部位
    private String beginOpertype;//开始-手术方式
    private String beginGree;//开始-知情同意
    private String beginPossign;//开始-手术部位标识
    private String beginAnasafecheck;//开始-麻醉安全检查
    private String beginBowatch;//开始-血氧检测
    private String beginPatientirritability;//开始-患者过敏史
    private String beginDyspnea;//开始-气道障碍或呼吸功能障碍
    private String beginDyspneamachine;//开始-设备提供支持
    private String beginVebachannel;//开始-静脉通道建立完成
    private String beginSkinfullcheckfrom;//开始-皮肤完整性检查
    private String beginTransfusionself;//开始-计划自体
    private String beginTransfusionfrom;//开始-异体输血
    private String beginTransfusion;//开始-是否输血
    private String beginImplantbody;//开始-假体
    private String beginIimplant;//开始-植入物
    private String beginImplantmetal;//开始-金属
    private String beginImplanthas;//开始-是否有有植入
    private String beginOther;//开始-其他

    private String pauseId;//暂停-患者身份
    private String pauseOperpos;//暂停-手术部位
    private String pauseOpertype;//暂停-手术方式
    private String pauseOpertw;//暂停-手术体位
    private String pauseFxyjdoctoryjsj;//暂停-预计手术时间
    private String pauseFxyjdoctoryjsxl;//暂停-预计失量
    private String pauseFxyjdoctorqdgzd;//暂停-手术医生强调关注点
    private String pauseFxyjanesthetistqdgzd;//暂停-麻醉医生强调关注点
    private String pauseFxyjanesthetistydfa;//暂停-麻醉医生应对方案
    private String pauseFxyjnursewpmjhg;//暂停-物品灭菌合格
    private String pauseFxyjnurseydfa;//暂停-手术护士应对方案
    private String pauseFxyjnurseyqsbwh;//暂停-仪器设备完好
    private String pause60kss;//暂停-手术前60分钟给予预防性抗生素
    private String pauseYxzl;//暂停-需要相关影像资料
    private String pauseQt;//暂停-其他

    private String endConfirm;//结束-记录实施的名称
    private String endRecoprname;//结束-患者过敏史
    private String endCount;//结束-清点手术用物
    private String endCountresult;//结束-清点结果（数量是否正确）
    private String endCountresultxray;//结束-清点结果X-ray
    private String endOprsampleconfirm;//结束-手术标本确认
    private String endOprsampleconfirmname;//结束-标本确认-患者姓名
    private String endOprsampleconfirmba;//结束-标本确认-病案号
    private String endSkinfullcheck;//结束-皮肤完整性检查
    private String endYlg;//结束-引流管
    private String endNg;//结束-尿管
    private String endQtgl;//结束-其他管路
    private String endMacneedrep;//结束-仪器设备是否需要检修
    private String endPatientto;//结束-病人去向
    private String endOther;//结束-其他

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

    public String getAnesthetist() {
        return anesthetist;
    }

    public void setAnesthetist(String anesthetist) {
        this.anesthetist = anesthetist;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
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

    public String getBeginId() {
        return beginId;
    }

    public void setBeginId(String beginId) {
        this.beginId = beginId;
    }

    public String getBeginOperpos() {
        return beginOperpos;
    }

    public void setBeginOperpos(String beginOperpos) {
        this.beginOperpos = beginOperpos;
    }

    public String getBeginOpertype() {
        return beginOpertype;
    }

    public void setBeginOpertype(String beginOpertype) {
        this.beginOpertype = beginOpertype;
    }

    public String getBeginGree() {
        return beginGree;
    }

    public void setBeginGree(String beginGree) {
        this.beginGree = beginGree;
    }

    public String getBeginPossign() {
        return beginPossign;
    }

    public void setBeginPossign(String beginPossign) {
        this.beginPossign = beginPossign;
    }

    public String getBeginAnasafecheck() {
        return beginAnasafecheck;
    }

    public void setBeginAnasafecheck(String beginAnasafecheck) {
        this.beginAnasafecheck = beginAnasafecheck;
    }

    public String getBeginBowatch() {
        return beginBowatch;
    }

    public void setBeginBowatch(String beginBowatch) {
        this.beginBowatch = beginBowatch;
    }

    public String getBeginPatientirritability() {
        return beginPatientirritability;
    }

    public void setBeginPatientirritability(String beginPatientirritability) {
        this.beginPatientirritability = beginPatientirritability;
    }

    public String getBeginDyspnea() {
        return beginDyspnea;
    }

    public void setBeginDyspnea(String beginDyspnea) {
        this.beginDyspnea = beginDyspnea;
    }

    public String getBeginDyspneamachine() {
        return beginDyspneamachine;
    }

    public void setBeginDyspneamachine(String beginDyspneamachine) {
        this.beginDyspneamachine = beginDyspneamachine;
    }

    public String getBeginVebachannel() {
        return beginVebachannel;
    }

    public void setBeginVebachannel(String beginVebachannel) {
        this.beginVebachannel = beginVebachannel;
    }

    public String getBeginSkinfullcheckfrom() {
        return beginSkinfullcheckfrom;
    }

    public void setBeginSkinfullcheckfrom(String beginSkinfullcheckfrom) {
        this.beginSkinfullcheckfrom = beginSkinfullcheckfrom;
    }

    public String getBeginTransfusionself() {
        return beginTransfusionself;
    }

    public void setBeginTransfusionself(String beginTransfusionself) {
        this.beginTransfusionself = beginTransfusionself;
    }

    public String getBeginTransfusionfrom() {
        return beginTransfusionfrom;
    }

    public void setBeginTransfusionfrom(String beginTransfusionfrom) {
        this.beginTransfusionfrom = beginTransfusionfrom;
    }

    public String getBeginTransfusion() {
        return beginTransfusion;
    }

    public void setBeginTransfusion(String beginTransfusion) {
        this.beginTransfusion = beginTransfusion;
    }

    public String getBeginImplantbody() {
        return beginImplantbody;
    }

    public void setBeginImplantbody(String beginImplantbody) {
        this.beginImplantbody = beginImplantbody;
    }

    public String getBeginIimplant() {
        return beginIimplant;
    }

    public void setBeginIimplant(String beginIimplant) {
        this.beginIimplant = beginIimplant;
    }

    public String getBeginImplantmetal() {
        return beginImplantmetal;
    }

    public void setBeginImplantmetal(String beginImplantmetal) {
        this.beginImplantmetal = beginImplantmetal;
    }

    public String getBeginImplanthas() {
        return beginImplanthas;
    }

    public void setBeginImplanthas(String beginImplanthas) {
        this.beginImplanthas = beginImplanthas;
    }

    public String getBeginOther() {
        return beginOther;
    }

    public void setBeginOther(String beginOther) {
        this.beginOther = beginOther;
    }

    public String getPauseId() {
        return pauseId;
    }

    public void setPauseId(String pauseId) {
        this.pauseId = pauseId;
    }

    public String getPauseOperpos() {
        return pauseOperpos;
    }

    public void setPauseOperpos(String pauseOperpos) {
        this.pauseOperpos = pauseOperpos;
    }

    public String getPauseOpertype() {
        return pauseOpertype;
    }

    public void setPauseOpertype(String pauseOpertype) {
        this.pauseOpertype = pauseOpertype;
    }

    public String getPauseOpertw() {
        return pauseOpertw;
    }

    public void setPauseOpertw(String pauseOpertw) {
        this.pauseOpertw = pauseOpertw;
    }

    public String getPauseFxyjdoctoryjsj() {
        return pauseFxyjdoctoryjsj;
    }

    public void setPauseFxyjdoctoryjsj(String pauseFxyjdoctoryjsj) {
        this.pauseFxyjdoctoryjsj = pauseFxyjdoctoryjsj;
    }

    public String getPauseFxyjdoctoryjsxl() {
        return pauseFxyjdoctoryjsxl;
    }

    public void setPauseFxyjdoctoryjsxl(String pauseFxyjdoctoryjsxl) {
        this.pauseFxyjdoctoryjsxl = pauseFxyjdoctoryjsxl;
    }

    public String getPauseFxyjdoctorqdgzd() {
        return pauseFxyjdoctorqdgzd;
    }

    public void setPauseFxyjdoctorqdgzd(String pauseFxyjdoctorqdgzd) {
        this.pauseFxyjdoctorqdgzd = pauseFxyjdoctorqdgzd;
    }

    public String getPauseFxyjanesthetistqdgzd() {
        return pauseFxyjanesthetistqdgzd;
    }

    public void setPauseFxyjanesthetistqdgzd(String pauseFxyjanesthetistqdgzd) {
        this.pauseFxyjanesthetistqdgzd = pauseFxyjanesthetistqdgzd;
    }

    public String getPauseFxyjanesthetistydfa() {
        return pauseFxyjanesthetistydfa;
    }

    public void setPauseFxyjanesthetistydfa(String pauseFxyjanesthetistydfa) {
        this.pauseFxyjanesthetistydfa = pauseFxyjanesthetistydfa;
    }

    public String getPauseFxyjnursewpmjhg() {
        return pauseFxyjnursewpmjhg;
    }

    public void setPauseFxyjnursewpmjhg(String pauseFxyjnursewpmjhg) {
        this.pauseFxyjnursewpmjhg = pauseFxyjnursewpmjhg;
    }

    public String getPauseFxyjnurseydfa() {
        return pauseFxyjnurseydfa;
    }

    public void setPauseFxyjnurseydfa(String pauseFxyjnurseydfa) {
        this.pauseFxyjnurseydfa = pauseFxyjnurseydfa;
    }

    public String getPauseFxyjnurseyqsbwh() {
        return pauseFxyjnurseyqsbwh;
    }

    public void setPauseFxyjnurseyqsbwh(String pauseFxyjnurseyqsbwh) {
        this.pauseFxyjnurseyqsbwh = pauseFxyjnurseyqsbwh;
    }

    public String getPause60kss() {
        return pause60kss;
    }

    public void setPause60kss(String pause60kss) {
        this.pause60kss = pause60kss;
    }

    public String getPauseYxzl() {
        return pauseYxzl;
    }

    public void setPauseYxzl(String pauseYxzl) {
        this.pauseYxzl = pauseYxzl;
    }

    public String getPauseQt() {
        return pauseQt;
    }

    public void setPauseQt(String pauseQt) {
        this.pauseQt = pauseQt;
    }

    public String getEndConfirm() {
        return endConfirm;
    }

    public void setEndConfirm(String endConfirm) {
        this.endConfirm = endConfirm;
    }

    public String getEndRecoprname() {
        return endRecoprname;
    }

    public void setEndRecoprname(String endRecoprname) {
        this.endRecoprname = endRecoprname;
    }

    public String getEndCount() {
        return endCount;
    }

    public void setEndCount(String endCount) {
        this.endCount = endCount;
    }

    public String getEndCountresult() {
        return endCountresult;
    }

    public void setEndCountresult(String endCountresult) {
        this.endCountresult = endCountresult;
    }

    public String getEndCountresultxray() {
        return endCountresultxray;
    }

    public void setEndCountresultxray(String endCountresultxray) {
        this.endCountresultxray = endCountresultxray;
    }

    public String getEndOprsampleconfirm() {
        return endOprsampleconfirm;
    }

    public void setEndOprsampleconfirm(String endOprsampleconfirm) {
        this.endOprsampleconfirm = endOprsampleconfirm;
    }

    public String getEndOprsampleconfirmname() {
        return endOprsampleconfirmname;
    }

    public void setEndOprsampleconfirmname(String endOprsampleconfirmname) {
        this.endOprsampleconfirmname = endOprsampleconfirmname;
    }

    public String getEndOprsampleconfirmba() {
        return endOprsampleconfirmba;
    }

    public void setEndOprsampleconfirmba(String endOprsampleconfirmba) {
        this.endOprsampleconfirmba = endOprsampleconfirmba;
    }

    public String getEndSkinfullcheck() {
        return endSkinfullcheck;
    }

    public void setEndSkinfullcheck(String endSkinfullcheck) {
        this.endSkinfullcheck = endSkinfullcheck;
    }

    public String getEndYlg() {
        return endYlg;
    }

    public void setEndYlg(String endYlg) {
        this.endYlg = endYlg;
    }

    public String getEndNg() {
        return endNg;
    }

    public void setEndNg(String endNg) {
        this.endNg = endNg;
    }

    public String getEndQtgl() {
        return endQtgl;
    }

    public void setEndQtgl(String endQtgl) {
        this.endQtgl = endQtgl;
    }

    public String getEndMacneedrep() {
        return endMacneedrep;
    }

    public void setEndMacneedrep(String endMacneedrep) {
        this.endMacneedrep = endMacneedrep;
    }

    public String getEndPatientto() {
        return endPatientto;
    }

    public void setEndPatientto(String endPatientto) {
        this.endPatientto = endPatientto;
    }

    public String getEndOther() {
        return endOther;
    }

    public void setEndOther(String endOther) {
        this.endOther = endOther;
    }

    public String getOperatorBegin() {
        return operatorBegin;
    }

    public void setOperatorBegin(String operatorBegin) {
        this.operatorBegin = operatorBegin;
    }

    public String getUseridBegin() {
        return useridBegin;
    }

    public void setUseridBegin(String useridBegin) {
        this.useridBegin = useridBegin;
    }

    public String getOperatordateBegin() {
        return operatordateBegin;
    }

    public void setOperatordateBegin(String operatordateBegin) {
        this.operatordateBegin = operatordateBegin;
    }

    public String getOperatorPause() {
        return operatorPause;
    }

    public void setOperatorPause(String operatorPause) {
        this.operatorPause = operatorPause;
    }

    public String getUseridPause() {
        return useridPause;
    }

    public void setUseridPause(String useridPause) {
        this.useridPause = useridPause;
    }

    public String getOperatordatePause() {
        return operatordatePause;
    }

    public void setOperatordatePause(String operatordatePause) {
        this.operatordatePause = operatordatePause;
    }

    public String getOperatorEnd() {
        return operatorEnd;
    }

    public void setOperatorEnd(String operatorEnd) {
        this.operatorEnd = operatorEnd;
    }

    public String getUseridEnd() {
        return useridEnd;
    }

    public void setUseridEnd(String useridEnd) {
        this.useridEnd = useridEnd;
    }

    public String getOperatordateEnd() {
        return operatordateEnd;
    }

    public void setOperatordateEnd(String operatordateEnd) {
        this.operatordateEnd = operatordateEnd;
    }

    public String getOperatorBegin2() {
        return operatorBegin2;
    }

    public void setOperatorBegin2(String operatorBegin2) {
        this.operatorBegin2 = operatorBegin2;
    }

    public String getUseridBegin2() {
        return useridBegin2;
    }

    public void setUseridBegin2(String useridBegin2) {
        this.useridBegin2 = useridBegin2;
    }

    public String getOperatordateBegin2() {
        return operatordateBegin2;
    }

    public void setOperatordateBegin2(String operatordateBegin2) {
        this.operatordateBegin2 = operatordateBegin2;
    }

    public String getOperatorPause2() {
        return operatorPause2;
    }

    public void setOperatorPause2(String operatorPause2) {
        this.operatorPause2 = operatorPause2;
    }

    public String getUseridPause2() {
        return useridPause2;
    }

    public void setUseridPause2(String useridPause2) {
        this.useridPause2 = useridPause2;
    }

    public String getOperatordatePause2() {
        return operatordatePause2;
    }

    public void setOperatordatePause2(String operatordatePause2) {
        this.operatordatePause2 = operatordatePause2;
    }

    public String getOperatorEnd2() {
        return operatorEnd2;
    }

    public void setOperatorEnd2(String operatorEnd2) {
        this.operatorEnd2 = operatorEnd2;
    }

    public String getUseridEnd2() {
        return useridEnd2;
    }

    public void setUseridEnd2(String useridEnd2) {
        this.useridEnd2 = useridEnd2;
    }

    public String getOperatordateEnd2() {
        return operatordateEnd2;
    }

    public void setOperatordateEnd2(String operatordateEnd2) {
        this.operatordateEnd2 = operatordateEnd2;
    }

    public String getOperatorBegin3() {
        return operatorBegin3;
    }

    public void setOperatorBegin3(String operatorBegin3) {
        this.operatorBegin3 = operatorBegin3;
    }

    public String getUseridBegin3() {
        return useridBegin3;
    }

    public void setUseridBegin3(String useridBegin3) {
        this.useridBegin3 = useridBegin3;
    }

    public String getOperatordateBegin3() {
        return operatordateBegin3;
    }

    public void setOperatordateBegin3(String operatordateBegin3) {
        this.operatordateBegin3 = operatordateBegin3;
    }

    public String getOperatorPause3() {
        return operatorPause3;
    }

    public void setOperatorPause3(String operatorPause3) {
        this.operatorPause3 = operatorPause3;
    }

    public String getUseridPause3() {
        return useridPause3;
    }

    public void setUseridPause3(String useridPause3) {
        this.useridPause3 = useridPause3;
    }

    public String getOperatordatePause3() {
        return operatordatePause3;
    }

    public void setOperatordatePause3(String operatordatePause3) {
        this.operatordatePause3 = operatordatePause3;
    }

    public String getOperatorEnd3() {
        return operatorEnd3;
    }

    public void setOperatorEnd3(String operatorEnd3) {
        this.operatorEnd3 = operatorEnd3;
    }

    public String getUseridEnd3() {
        return useridEnd3;
    }

    public void setUseridEnd3(String useridEnd3) {
        this.useridEnd3 = useridEnd3;
    }

    public String getOperatordateEnd3() {
        return operatordateEnd3;
    }

    public void setOperatordateEnd3(String operatordateEnd3) {
        this.operatordateEnd3 = operatordateEnd3;
    }
}