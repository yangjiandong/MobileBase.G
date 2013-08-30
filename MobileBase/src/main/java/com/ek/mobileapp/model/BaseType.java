
package com.ek.mobileapp.model;

import com.alibaba.fastjson.JSON;

public class BaseType {
    public String toJson() {
        //JSON.parseObject(p.toString(), CommDict.class);
        return JSON.toJSONString(this);
        //};().toJson(this);
    }
}
