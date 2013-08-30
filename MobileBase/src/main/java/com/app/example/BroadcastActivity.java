package com.app.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.service.BlueToothService;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobilebapp.R;

public class BroadcastActivity extends AppActivity {
    TextView scanBarcodeTextView;
    Button sendButton;
    MyReceiver receiver;
    Intent blueToothService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_service_layout);

        scanBarcodeTextView = (TextView) findViewById(R.id.scan_barCode);
        //myTextView.setText("Season");
        blueToothStyle_unConn(R.id.scan_barCode);

        sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new SendButtonClickListener());

        blueToothService = new Intent(this, BlueToothService.class);
        startService(blueToothService);
    }

    public class SendButtonClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            byte command = 45;
            int value = 0x12345;
            sendCmd(command, value);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (receiver != null) {
            BroadcastActivity.this.unregisterReceiver(receiver);
        }

        //
        stopService(blueToothService);
    }

    @Override
    protected void onResume() {
        super.onResume();

        receiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BlueToothService.CMD_ACTION);
        BroadcastActivity.this.registerReceiver(receiver, filter);
    }

    public void showToast(String str) {//显示提示信息
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    //接受服务信息
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BlueToothService.CMD_ACTION)) {
                Bundle bundle = intent.getExtras();
                int cmd = bundle.getInt("cmd");

                if (cmd == BlueToothService.CMD_SHOW_TOAST) {
                    //不显示
                    String str = bundle.getString("str");
                    Logger.d(str);
                }

                if (cmd == BlueToothService.CMD_SEND_READER) {
                    String str = bundle.getString("str");
                    scanBarcodeTextView.setText(str);
                }

                if (cmd == BlueToothService.CMD_SEND_INFO) {
                    String str = bundle.getString("str");
                    if (str.equals("1")) {
                        blueToothStyle_conn(R.id.scan_barCode);
                    }
                }

                else if (cmd == BlueToothService.CMD_SYSTEM_EXIT) {
                    System.exit(0);
                }

            }
        }
    }

    public void sendCmd(byte command, int value) {
        Intent intent = new Intent();//创建Intent对象
        intent.setAction(BlueToothService.CMD);
        intent.putExtra("cmd", BlueToothService.CMD_SEND_DATA);
        intent.putExtra("command", command);
        intent.putExtra("value", value);
        sendBroadcast(intent);//发送广播
    }

    protected void blueToothStyle_conn(int id) {
        EditText editText = (EditText) findViewById(id);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bluetooth_y, 0, 0, 0);
        editText.setHint("请扫条码");
    }

    protected void blueToothStyle_unConn(int id) {
        EditText editText = (EditText) findViewById(id);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bluetooth_n, 0, 0, 0);
        editText.setHint("请连接蓝牙设备");
    }
}