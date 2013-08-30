package com.ek.mobileapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.ek.mobileapp.AppConfig;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobileapp.utils.bluetooth.UIDSBluetooth;

//http://blog.csdn.net/cen616899547/article/details/6728040
public class BlueToothService extends Service {

    public boolean threadFlag = true;
    MyThread myThread;
    CommandReceiver cmdReceiver;//继承自BroadcastReceiver对象，用于得到Activity发送过来的命令

    /**************service 命令*********/
    public static final int CMD_STOP_SERVICE = 0x01;
    public static final int CMD_SEND_DATA = 0x02;
    public static final int CMD_SYSTEM_EXIT = 0x03;
    public static final int CMD_SHOW_TOAST = 0x04;
    public static final int CMD_SEND_READER = 0X05;//发送扫入信息
    public static final int CMD_SEND_INFO = 0x06;//发送相关信息

    public static final String CMD_ACTION = "android.intent.action.lxx";
    public static final String CMD = "android.intent.action.cmd";
    public static final String ERROR = "error99";

    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    private InputStream inStream = null;
    public boolean bluetoothFlag = true;
    static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    MainApplication ac;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Logger.d("BlueToothService onCreate");
        super.onCreate();

        ac = (MainApplication) getApplication();
    }

    //前台Activity调用startService时，该方法自动执行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.d("BlueToothService onStartCommand");

        cmdReceiver = new CommandReceiver();
        IntentFilter filter = new IntentFilter();//创建IntentFilter对象
        //注册一个广播，用于接收Activity传送过来的命令，控制Service的行为，如：发送数据，停止服务等
        filter.addAction(CMD);
        //注册Broadcast Receiver
        registerReceiver(cmdReceiver, filter);
        doJob();//调用方法启动线程

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.d("BlueToothService Destory...");
        super.onDestroy();

        this.unregisterReceiver(cmdReceiver);//取消注册的CommandReceiver
        threadFlag = false;

        try {
            if (btSocket != null)
                btSocket.close();
        } catch (IOException e) {
            Logger.e(e.getMessage());
        }

        //boolean retry = true;
        //while (retry) {
        //    try {
        //        myThread.join();
        //        retry = false;
        //    } catch (Exception e) {
        //        Logger.d(e.getMessage());
        //        //e.printStackTrace();
        //    }
        //
        //}
    }

    public void doJob() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            showToast("蓝牙设备不可用，请打开蓝牙！");
            bluetoothFlag = false;
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            //log("请打开蓝牙并重新运行程序！");
            bluetoothFlag = false;
            stopService();
            showToast("请打开蓝牙并重新运行程序！");
            return;
        }
        String deviceAddress = ac.getBlueToothDeviceAddress();
        if (deviceAddress.equals("")) {
            stopService();
            showToast("没找到蓝牙设备,请配置好蓝牙后再使用该功能！");
            return;
        }
        showToast("搜索到蓝牙设备!");
        threadFlag = true;
        myThread = new MyThread();
        myThread.start();
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();

            connectDevice();//连接蓝牙设备
            while (threadFlag) {
                sendInfo("1");

                String value = readByte();
                if (!value.equals(ERROR)) {
                    log(value + "");

                    sendReader(value);
                }

                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void connectDevice() {
        showToast("正在尝试连接蓝牙设备，请稍后····");
        String deviceAddress = ac.getBlueToothDeviceAddress();
        if (deviceAddress.equals("")) {

        }
        BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(deviceAddress);
        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
            log("套接字创建失败！");
            bluetoothFlag = false;
        }

        mBluetoothAdapter.cancelDiscovery();
        try {
            //TODO
            //重新连接时会报设备忙的信息但不影响使用
            btSocket.connect();
            //log("连接成功建立，可以开始操控了!");
            showToast("连接成功建立，可以开始操控了!");
            sendInfo("1");

            bluetoothFlag = true;
        } catch (IOException e) {
            try {
                btSocket.close();
                bluetoothFlag = false;
            } catch (IOException e2) {
                log("连接没有建立，无法关闭套接字！");
            }
        }

        if (bluetoothFlag) {
            try {
                inStream = btSocket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            } //绑定读接口

            try {
                outStream = btSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            } //绑定写接口

        }
    }

    public void sendCmd(byte cmd, int value)//串口发送数据
    {
        if (!bluetoothFlag) {
            return;
        }
        byte[] msgBuffer = new byte[5];
        msgBuffer[0] = cmd;
        msgBuffer[1] = (byte) (value >> 0 & 0xff);
        msgBuffer[2] = (byte) (value >> 8 & 0xff);
        msgBuffer[3] = (byte) (value >> 16 & 0xff);
        msgBuffer[4] = (byte) (value >> 24 & 0xff);

        try {
            outStream.write(msgBuffer, 0, 5);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readByte() {//return -1 if no data
        String ret = ERROR;
        if (!bluetoothFlag) {
            return ret;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inStream));

            if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_UIDS)) {
                UIDSBluetooth uids = new UIDSBluetooth();
                String receive = uids.changeBarCode(inStream);
                if (!UtilString.isBlank(UIDSBluetooth.EvaluateCardNumber(receive))) {
                    ret = UIDSBluetooth.EvaluateCardNumber(receive);
                }
            } else if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_ZBK)) {
                //解决分段流问题,结束标志0A 0D
                boolean isend = false;
                char[] line = new char[128];
                reader.read(line);
                for (int i = 0; i < line.length; i++) {
                    char c = line[i];
                    if (c != '\0') {
                    }
                    if (c == '\n') {
                        isend = true;
                    }
                }
                StringBuffer all = new StringBuffer(0);
                String tmp = new String(line).trim();
                //0A 0D
                all.append(tmp);

                if (isend) {
                    String receive = all.toString().trim();//new String(line).trim();
                    all.setLength(0);
                    ret = receive;
                } else {

                }

            } else if (ac.getBlueToothDeviceType().equals(AppConfig.BLUETOOTH_COMMON)) {
                char[] line = new char[1024];
                reader.read(line);
                ret = new String(line).trim();
            }
        } catch (IOException e) {
            Logger.e(e.getMessage());
        } finally {
            //TODO 不能关闭
            //if (reader != null)
            //    try {
            //        reader.close();
            //    } catch (IOException e) {
            //        Logger.e(e.getMessage());
            //    }
        }

        return ret;
    }

    public void stopService() {//停止服务
        threadFlag = false;//停止线程
        stopSelf();//停止服务
    }

    public void showToast(String str) {//前台显示提示信息
        Intent intent = new Intent();
        intent.putExtra("cmd", CMD_SHOW_TOAST);
        intent.putExtra("str", str);
        intent.setAction(CMD_ACTION);
        sendBroadcast(intent);
    }

    public void sendReader(String str) {//发送蓝牙信息
        Intent intent = new Intent();
        intent.putExtra("cmd", CMD_SEND_READER);
        intent.putExtra("str", str);
        intent.setAction(CMD_ACTION);
        sendBroadcast(intent);
    }

    public void sendInfo(String str) {//显示服务相关信息
        Intent intent = new Intent();
        intent.putExtra("cmd", CMD_SEND_INFO);
        intent.putExtra("str", str);
        intent.setAction(CMD_ACTION);
        sendBroadcast(intent);
    }

    public void log(String str) {
        Logger.d(str);
    }

    //接收Activity传送过来的命令
    private class CommandReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CMD)) {
                int cmd = intent.getIntExtra("cmd", -1);//获取Extra信息
                if (cmd == CMD_STOP_SERVICE) {
                    stopService();
                } else if (cmd == CMD_SEND_DATA) {
                    byte command = intent.getByteExtra("command", (byte) 0);
                    int value = intent.getIntExtra("value", 0);
                    sendCmd(command, value);
                }
            }
        }
    }
}
