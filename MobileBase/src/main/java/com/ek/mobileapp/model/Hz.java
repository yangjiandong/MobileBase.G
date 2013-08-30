package com.ek.mobileapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;

import com.alibaba.fastjson.JSON;
import com.ek.mobileapp.dao.HzDataHelper;

public class Hz extends BaseType implements Serializable {
    private static final HashMap<Long, Hz> CACHE = new HashMap<Long, Hz>();

    private static final long serialVersionUID = 5901172410699752206L;
    private Long id;
    private String hz;
    private String wb;
    private String py;
    String created_at;

    public Hz() {
    }

    public Hz(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Team name must not be null");
        }
        this.hz = name.trim();
    }

    public Hz(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHz() {
        return hz;
    }

    public void setHz(String hz) {
        this.hz = hz;
    }

    public String getWb() {
        return wb;
    }

    public void setWb(String wb) {
        this.wb = wb;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    private static void addToCache(Hz shot) {
        CACHE.put(shot.getId(), shot);
    }

    private static Hz getFromCache(long id) {
        return CACHE.get(id);
    }

    public static Hz fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(HzDataHelper.HzDBInfo._ID));//_ID
        Hz entity = getFromCache(id);
        if (entity != null) {
            return entity;
        }
        //String shz = cursor.getString(cursor.getColumnIndex(HzDataHelper.HzDBInfo.HZ));
        //String py = cursor.getString(cursor.getColumnIndex(HzDataHelper.HzDBInfo.PY));
        //String wb = cursor.getString(cursor.getColumnIndex(HzDataHelper.HzDBInfo.WB));
        //entity = new Hz(id, shz);
        //entity.setPy(py);
        //entity.setWb(wb);

        entity = JSON.parseObject(cursor.getString(cursor.getColumnIndex(HzDataHelper.HzDBInfo.JSON)), Hz.class);
        addToCache(entity);

        return entity;
    }

    public static class HzsRequestData {
        int page;
        int pages;
        int times;
        private ArrayList<Hz> hzs;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public ArrayList<Hz> getHzs() {
            return hzs;
        }

        public void setHzs(ArrayList<Hz> hzs) {
            this.hzs = hzs;
        }

    }

    public static class HzsRequestPagesData {
        int pages;
        int times;

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }
}
