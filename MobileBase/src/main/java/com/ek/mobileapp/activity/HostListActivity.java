package com.ek.mobileapp.activity;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ek.mobileapp.MainApplication;
import com.ek.mobilebapp.R;

//服务地址列表
public class HostListActivity extends Activity {
    MainApplication ac;

    // Intent request codes
    public static final int REQUEST_HOST_SET = 2;

    // Debugging
    private static final String TAG = "hOSTListActivity";
    private static final boolean D = true;

    private ArrayAdapter<String> mHostsArrayAdapter;
    HashMap<String, String> hosts;

    // Return Intent extra
    public static String EXTRA_HOST_NAME = "host_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.host_list);

        ac = (MainApplication) getApplication();

        // Set result CANCELED incase the user backs out
        setResult(Activity.RESULT_CANCELED);

        String as = ac.getHostList();
        List<String> barList1 = JSON.parseArray(as, String.class);

        mHostsArrayAdapter = new ArrayAdapter<String>(this, R.layout.host_name);
        hosts = new HashMap<String, String>();

        ListView listHost = (ListView) findViewById(R.id.host_list);
        for (String u : barList1) {
            String s = u;
            hosts.put(s, u);
            mHostsArrayAdapter.add(s);
        }
        listHost.setAdapter(mHostsArrayAdapter);
        listHost.setOnItemClickListener(mDeviceClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            String info = ((TextView) v).getText().toString();
            String u = hosts.get(info);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_HOST_NAME, u);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

}
