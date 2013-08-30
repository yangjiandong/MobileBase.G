package com.app.example.dao;

import android.content.Context;

public class DAOFactory {
    private static DAOFactory instance = null;

    private Context globalContext = null;
    private boolean cacheDAOInstances = false;
    private HzDAO cachedHzDAO = null;

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    private DAOFactory() {
    }

    public HzDAO getHzDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedHzDAO == null) {
                cachedHzDAO = new HzDAO(getProperDAOContext(context));
            }
            return cachedHzDAO;
        } else {
            return new HzDAO(getProperDAOContext(context));
        }
    }

    public void setGlobalContext(Context context) {
        globalContext = context;
    }

    public void setCacheDAOInstances(boolean cacheDAOInstances) {
        this.cacheDAOInstances = cacheDAOInstances;
    }

    private Context getProperDAOContext(Context context) {
        if (globalContext != null) {
            return globalContext;
        } else {
            return context;
        }
    }
}
