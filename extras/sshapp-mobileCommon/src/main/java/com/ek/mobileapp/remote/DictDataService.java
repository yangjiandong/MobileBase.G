package com.ek.mobileapp.remote;

import java.util.List;
import java.util.Map;

import com.ek.mobileapp.model.DrugDict;
import com.ek.mobileapp.model.DrugDoctorRight;
import com.ek.mobileapp.model.DrugInfoDict;
import com.ek.mobileapp.model.DrugStork;
import com.ek.mobileapp.model.ItemDict;
import com.ek.mobileapp.model.OperationDict;
import com.ek.mobileapp.model.PerformDefaultSchedule;

public interface DictDataService {
    List<DrugDict> getDrugDict();

    //全部,客户端下载慢
    Map<String, Object> getAllDict();

    List<ItemDict> getItemDict();

    List<OperationDict> getOperationDict();

    List<DrugInfoDict> getDrugInfoDict();

    List<DrugDoctorRight> getDrugDoctorRight();

    List<DrugStork> getDrugStork();

    List<PerformDefaultSchedule> getPerformDefaultSchedule();
}
