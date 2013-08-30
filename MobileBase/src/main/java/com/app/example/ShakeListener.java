package com.app.example;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.ek.mobileapp.utils.Logger;

//摇一摇
public class ShakeListener implements SensorEventListener{
    //速度阈值，当摇晃速度达到这值后产生作用
    static final int SPEED_SHRESHOLD = 3000;
    //两次检测的时间间隔
    static final int UPDATE_INTERVAL_TIME = 100;

    SensorManager sensorManager;
    Sensor sensor;
    //重力感应监听器
    OnShakeListener onShakeListener;
    Context mContext;
    //位置
    float lastX;
    float lastY;
    float lastZ;
    //
    long lastUpdateTime;

    public ShakeListener(Context c){
        mContext = c;
        start();
    }

    public void start() {
        sensorManager = (SensorManager)mContext
                .getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }
        if (sensor != null) {
            sensorManager.registerListener(this, sensor,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    //
    public void stop(){
        sensorManager.unregisterListener(this);
    }

    // 设置重力感应监听器
    public void setOnShakeListener(OnShakeListener listener) {
        onShakeListener = listener;
    }

    //重力感应器感应获得变化数据
    public void onSensorChanged(SensorEvent event){
        long currentUpdateTime = System.currentTimeMillis();
        long timeInterval = currentUpdateTime - lastUpdateTime;
        //
        if (timeInterval< UPDATE_INTERVAL_TIME)
            return;

        lastUpdateTime = currentUpdateTime;
        //
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;

        lastX = x;
        lastY = y;
        lastZ = z;

        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ)/timeInterval * 10000;
        if (speed >= SPEED_SHRESHOLD){
            Logger.d("摇一摇...");
            onShakeListener.onShake();
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public interface OnShakeListener{
        public void onShake();
    }

}
