package com.app.example;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.example.ShakeListener.OnShakeListener;
import com.app.example.dashboard.DashboardMain;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.activity.AppActivity;
import com.ek.mobileapp.activity.SettingActivity;
import com.ek.mobileapp.utils.DialogFactory;
import com.ek.mobileapp.utils.DialogFactory.DialogCallbacks;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.volley.StringRequest;
import com.ek.mobileapp.widget.common.AppContent;
import com.ek.mobilebapp.R;

public class DashboardActivity extends AppActivity {
    ShakeListener mShakeListener = null;
    AppContent appContent;
    TextView title;
    Button leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createMenuts();
        updateTitle();

        sensorInit();
        volleyQuery();
    }

    void volleyQuery(){
        //volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String ip = MainApplication.host_ip;
        String url = "http://" + ip + "/health_education/query_type";
        StringRequest jsobjRequest = new StringRequest(Request.Method.GET, url, null,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //title.setText("Response=>"+response.toString());
                        Logger.d("Response=>" + response.toString());
                        //findViewById(R.id.progressBar1).setVisibility(View.GONE);
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError verror) {
                        Logger.e(verror.toString());
                    }
                });
        queue.add(jsobjRequest);
    }

    void updateTitle() {
        //update to more icon
        leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(com.ek.mobilebapp.R.drawable.more_icon), null, null);
        leftBtn.setOnClickListener(clickListener);

        title = (TextView) findViewById(R.id.custom_title_label);
        title.setText(R.string.app_name);
    }

    void createMenuts() {
        createMainContent();
        View menuContext = this.appContent;
        setContentView(R.layout.dashboard);

        ScrollView sView = (ScrollView) findViewById(R.id.modules);
        sView.addView(menuContext);
    }

    void createMainContent() {
        //if(useTabletLayout())
        //obj = new TabletDashboard(this);
        //else
        //    obj =
        appContent = new DashboardMain(this);
    }

    View.OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {

            if (v.equals(leftBtn)) {
                Logger.d("show Setting");
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        };
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit(RESULT_OK, "确认退出程序");
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit(final int result, String msg) {

        DialogCallbacks dCalls = new DialogCallbacks() {

            @Override
            public void runOk(DialogInterface dialog, int which) {
                finish();
                //MainApplication.AppExit(MainActivity.this);
                System.exit(0);
            }

            @Override
            public void runCancel(DialogInterface dialog, int which) {

            }
        };

        Dialog dialog = DialogFactory.create(this, "确认退出", "", dCalls);
        dialog.show();
    }

    void sensorInit() {
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new OnShakeListener() {

            public void onShake() {
                ToastUtils.show(DashboardActivity.this, "摇了一下...");
            }
        });
    }

    @Override
    protected void onDestroy() {

        mShakeListener.stop();
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
