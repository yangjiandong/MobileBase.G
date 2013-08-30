package com.ek.mobileapp.model;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    protected int id;

    public int getId() {
        return id;
    }

    protected String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }
}
