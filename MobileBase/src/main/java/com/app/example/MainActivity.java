package com.app.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.MyAppResource;
import com.app.example.ShakeListener.OnShakeListener;
import com.app.example.service.UpdateUIService;
import com.app.example.service.UpdateUIService.MyBinder;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.action.MobLogAction;
import com.ek.mobileapp.activity.SettingActivity;
import com.ek.mobileapp.utils.DialogFactory;
import com.ek.mobileapp.utils.DialogFactory.DialogCallbacks;
import com.ek.mobileapp.utils.ImageUtils;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.UIHelper;
import com.ek.mobileapp.utils.UtilString;
import com.ek.mobileapp.widget.HomeImageButton;
import com.ek.mobileapp.widget.ScreenShotView;
import com.ek.mobileapp.widget.ScreenShotView.OnScreenShotListener;
import com.ek.mobilebapp.R;

public class MainActivity extends Activity {
    ShakeListener mShakeListener = null;
    String menus = "01|列表,02|汉字,03|字母索引,04|图片截取,05|屏幕截图";

    TextView title;
    Button leftBtn;

    Map<String, Integer> btns = new HashMap<String, Integer>();
    Map<String, int[]> btnsStyle = new HashMap<String, int[]>();

    //binder service
    TextView main_message_count;
    UpdateUIService.MyBinder binder;
    ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.d("onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.d("onServiceConnected");
            binder = (MyBinder) service;
            main_message_count.setText("ok");
        }
    };

    private int screenWidth;
    private int scrollHeight;

    private void getSize() {
        RelativeLayout titleView = (RelativeLayout) findViewById(R.id.custome_title_id);
        RelativeLayout userView = (RelativeLayout) findViewById(R.id.main_User_layout);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        scrollHeight = display.getHeight() - titleView.getHeight() - userView.getHeight() - 20;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createBtns();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(MyAppResource.LayoutMain);

        //update to more icon
        leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(null,
                getResources().getDrawable(com.ek.mobilebapp.R.drawable.more_icon), null, null);
        leftBtn.setOnClickListener(clickListener);

        title = (TextView) findViewById(R.id.custom_title_label);
        title.setText(R.string.app_name);
        title.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder != null) {
                    binder.setDate(main_message_count, new UpdateUIService.UpdateData() {
                        @Override
                        public void update(TextView tv, int data) {
                            tv.setText(data + "");
                        }
                    });
                } else {
                    ToastUtils.show(getApplicationContext(), "no..");
                }
            }
        });

        main_message_count = (TextView) findViewById(R.id.main_message_count);
        //main_message_count.setVisibility(View.VISIBLE);
        Intent updateService = new Intent(this, UpdateUIService.class);
        bindService(updateService, conn, Context.BIND_AUTO_CREATE);

        //
        getSize();

        //volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String ip = MainApplication.host_ip;
        String url = "http://" + ip + "/common/get_sys_cur_date";
        JsonObjectRequest jsobjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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

        showUserPermission();
        sensorInit();
    }

    View.OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {
            Logger.d("click:" + v);

            //
            if (v.equals(leftBtn)) {
                Logger.d("show Setting");
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.m01) {
                Intent intent = new Intent(MainActivity.this, EndlessListActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.m02) {
                Intent intent = new Intent(MainActivity.this, HzListActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.m03) {
                Intent intent = new Intent(MainActivity.this, LetterListActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.m04) {
                Intent intent = new Intent(MainActivity.this, ShootAndCropActivity.class);
                startActivity(intent);
            } else if (v.getId() == R.id.m05) {
                shot("", "");
            }
        };
    };

    void shot(final String title, final String url) {

        UIHelper.addScreenShot(MainActivity.this, new OnScreenShotListener() {

            @SuppressLint("NewApi")
            public void onComplete(Bitmap bm) {
                Intent intent = new Intent(MainActivity.this, com.ek.mobileapp.activity.ScreenShotShare.class);
                intent.putExtra("title", title);
                intent.putExtra("url", url);
                intent.putExtra("cut_image_tmp_path", ScreenShotView.TEMP_SHARE_FILE_NAME);
                try {
                    ImageUtils.saveImageToSD(MainActivity.this, ScreenShotView.TEMP_SHARE_FILE_NAME, bm, 100);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MainActivity.this.startActivity(intent);
            }
        });
    }

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

    void createBtns() {
        btns.put("01", R.id.m01);
        btns.put("02", R.id.m02);
        btns.put("03", R.id.m03);
        btns.put("04", R.id.m04);
        btns.put("05", R.id.m05);
        btns.put("06", R.id.m06);
        btns.put("07", R.id.m07);
        btns.put("08", R.id.m08);
        btns.put("09", R.id.m09);
        btns.put("10", R.id.m10);
        btns.put("11", R.id.m11);
        btns.put("12", R.id.m12);

        btnsStyle.put("01", new int[] { R.drawable.home_grid_item_blue3, R.drawable.db_icon_appointment });
        btnsStyle.put("02", new int[] { R.drawable.home_grid_item_green, R.drawable.db_icon_appointment });
        btnsStyle.put("03", new int[] { R.drawable.home_grid_item_orange, R.drawable.db_icon_appointment });
        btnsStyle.put("04", new int[] { R.drawable.home_grid_item_blue2, R.drawable.db_icon_appointment });
        btnsStyle.put("05", new int[] { R.drawable.home_grid_item_red2, R.drawable.db_icon_appointment });
        btnsStyle.put("06", new int[] { R.drawable.home_grid_item_purple, R.drawable.db_icon_appointment });
        btnsStyle.put("07", new int[] { R.drawable.home_grid_item_yellow, R.drawable.db_icon_appointment });
        btnsStyle.put("08", new int[] { R.drawable.home_grid_item_blue, R.drawable.db_icon_appointment });
        btnsStyle.put("09", new int[] { R.drawable.home_grid_item_red, R.drawable.db_icon_appointment });

        //btnsStyle.put("01", R.drawable.home_grid_item_blue3);

    }

    @SuppressWarnings("unchecked")
    void showUserPermission() {

        try {
            //一排三个按钮
            int count = 3;
            TableLayout modules = (TableLayout) findViewById(R.id.modules);
            //先清除，切换用户时重新显示
            modules.removeAllViews();

            List<String> allIds = UtilString.stringToArrayList(menus, ",");
            int i = 0;
            TableRow one = null;
            for (String oneModule : allIds) {
                StringTokenizer filter = new StringTokenizer(oneModule, "|");
                String code = filter.nextToken();
                String module = filter.nextToken();

                if (i == count) {
                    i = 0;
                }

                if (i == 0) {
                    one = (TableRow) getLayoutInflater().inflate(R.layout.dash_table_row, null);
                    TableLayout.LayoutParams t_params = new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    one.setGravity(Gravity.CENTER);
                    modules.addView(one, t_params);
                }

                Integer id = (Integer) btns.get(code);
                if (id != null) {
                    HomeImageButton bn = new HomeImageButton(getApplicationContext(), null);
                    bn.setId(btns.get(code));
                    bn.setBackground(this.btnsStyle.get(code)[0]);
                    bn.setSrc(this.btnsStyle.get(code)[1]);
                    bn.setText(module);
                    bn.setSize((screenWidth) / count, scrollHeight / 6);

                    one.addView(bn);

                    //为按钮绑定一个事件监听器
                    bn.setOnClickListener(clickListener);

                    i++;
                }
            }

        } catch (Exception e) {
            MobLogAction.getMobLogAction().mobLogError("main", e.getMessage());
        }
    }

    void sensorInit() {
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new OnShakeListener() {

            public void onShake() {
                ToastUtils.show(MainActivity.this, "摇了一下...");
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
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
