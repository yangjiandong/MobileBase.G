package com.ek.mobileapp;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;

//单例
public class ApplicationConfig {
    private List<Activity> activitys = null;
    private static ApplicationConfig instance;
    // 创建一个可重用固定线程数的线程池
    private static ExecutorService pool;

    public static ExecutorService getPool() {
        return pool;
    }

    // 线程池大小
    private static final int POOL_SIZE = 10;
    // 轮询时间
    private final int SLEEP_TIME = 1000;
    // 是否停止
    private boolean isStop = false;

    private ApplicationConfig() {
        activitys = new LinkedList<Activity>();
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static ApplicationConfig getInstance() {
        if (null == instance) {
            instance = new ApplicationConfig();
            int cpuNums = Runtime.getRuntime().availableProcessors();
            pool = Executors.newFixedThreadPool(cpuNums * POOL_SIZE);
            //newFixedThreadPool - ;创建固定大小(nThreads,大小不能超过int的最大值)的线程池
            //newSingleThreadExecutor()：创建大小为1的固定线程池。
            //newCachedThreadPool()；创建corePoolSize为0，最大线程数为整型的最大数，线程keepAliveTime为1分钟，缓存任务的队列为SynchronousQueue的线程池。
            //newScheduledThreadPool(int corePoolSize):创建corePoolSize大小的线程池。

        }
        return instance;

    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        pool.shutdown();
        System.exit(0);
    }

    public static boolean isDebuggable(Context c) {
        return (0 != (c.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
    }
}
