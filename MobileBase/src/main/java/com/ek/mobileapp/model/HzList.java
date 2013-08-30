package com.ek.mobileapp.model;

import java.util.ArrayList;
import java.util.List;

public class HzList extends Entity{
    List<Hz>hzs= new ArrayList<Hz>();

    public List<Hz> getHzs() {
        return hzs;
    }

    public void setHzs(List<Hz> hzs) {
        this.hzs = hzs;
    }

}
