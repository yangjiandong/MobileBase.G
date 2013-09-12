package com.oy.activity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
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

import com.android.internal.telephony.ITelephony;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.MyAppResource;
import com.app.example.EndlessListActivity;
import com.app.example.HzListActivity;
import com.app.example.LetterListActivity;
import com.app.example.ShakeListener;
import com.app.example.ShakeListener.OnShakeListener;
import com.app.example.ShootAndCropActivity;
import com.app.example.service.UpdateUIService;
import com.app.example.service.UpdateUIService.MyBinder;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.action.MobLogAction;
import com.ek.mobileapp.activity.SettingActivity;
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.ui.BadgeView;
import com.ek.mobileapp.utils.DialogFactory;
import com.ek.mobileapp.utils.DialogFactory.DialogCallbacks;
import com.ek.mobileapp.utils.GlobalCache;
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
    private static final String TAG = "MainActivity";

    ShakeListener mShakeListener = null;
    MainApplication ac;

    Map<Integer, String> moduels = new HashMap<Integer, String>();

    TextView title, main_logonUser;
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
            //main_message_count.setText("ok");
            showText(main_message_count, "ok");
        }
    };

    int MAX = 0;
    ProgressDialog mProgressDlg;

    private TelephonyManager tManager = null;
    private ITelephony iTelephony = null;
    //占线时转移，提示所拨的号码为空号
    //private final String ENABLE_SERVICE = "tel:**67*13800000000%23";
    //占线时转移，提示所拨的号码为关机
    //private final String ENABLE_POWEROFF_SERVICE = "tel:**67*13810538911%23";
    //占线时转移，提示所拨的号码为停机
    //private final String ENABLE_STOP_SERVICE = "tel:**21*13701110216%23";
    //占线时转移
    //private final String DISABLE_SERVICE = "tel:%23%2321%23";

    //通知
    public static com.ek.mobileapp.ui.BadgeView bv_active;

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

        ac = (MainApplication) getApplication();

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

        main_message_count = (TextView) findViewById(R.id.main_message_count);
        main_message_count.setVisibility(View.VISIBLE);
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

        initBadgeView();

        //启动轮询通知信息
        //this.foreachUserNotice();

        sensorInit();

        telRefuse();
    }

    void telRefuse() {

        //打开监听电话功能
        TelephonyManager mTelephonyMgr = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyMgr.listen(new TeleListener(), PhoneStateListener.LISTEN_CALL_STATE);

        tManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //初始化iTelephony
        Class<TelephonyManager> c = TelephonyManager.class;
        Method getITelephonyMethod = null;
        try {
            getITelephonyMethod = c.getDeclaredMethod("getITelephony", (Class[]) null);
            getITelephonyMethod.setAccessible(true);
        } catch (SecurityException e) {
            Logger.e("SecurityException:" + e.getMessage());
        } catch (NoSuchMethodException e) {
            Logger.e("NoSuchMethodException:" + e.getMessage());
        }

        try {
            iTelephony = (ITelephony) getITelephonyMethod.invoke(tManager, (Object[]) null);
        } catch (IllegalArgumentException e) {
            Logger.e("IllegalArgumentException:" + e.getMessage());
        } catch (IllegalAccessException e) {
            Logger.e("IllegalAccessException:" + e.getMessage());
        } catch (InvocationTargetException e) {
            Logger.e("InvocationTargetException:" + e.getMessage());
        }

        //启用空号提示
        //Intent i = new Intent(Intent.ACTION_CALL);
        //i.setData(Uri.parse(ENABLE_STOP_SERVICE));
        //startActivity(i);
        //Logger.v("启用空号提示");
    }

    /**
     * 初始化通知信息标签控件
     */
    private void initBadgeView() {
        bv_active = new BadgeView(this, main_logonUser);
        bv_active.setBackgroundResource(R.drawable.widget_count_bg);
        bv_active.setIncludeFontPadding(false);
        bv_active.setGravity(Gravity.CENTER);
        bv_active.setTextSize(8f);
        bv_active.setTextColor(Color.WHITE);
    }

    View.OnClickListener clickListener = new OnClickListener() {
        public void onClick(View v) {

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
    }

    @SuppressWarnings("unchecked")
    void showUserPermission() {
        showUserIfo();

        try {
            //一排三个按钮
            int count = 3;
            TableLayout modules = (TableLayout) findViewById(R.id.modules);
            //先清除，切换用户时重新显示
            modules.removeAllViews();

            UserDTO user = GlobalCache.getCache().getLoginuser();
            String alls = user.getMobmodules();
            List<String> allIds = UtilString.stringToArrayList(alls, ",");
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

    //show user info
    private void showUserIfo() {
        UserDTO user = GlobalCache.getCache().getLoginuser();
        main_logonUser = (TextView) findViewById(R.id.main_logonUser);
        try {
            main_logonUser.setText("当前用户:" + user.getName() + " - "
                    + user.getDepartName().replace("护理组", "").replace("护理单元", ""));
        } catch (Exception e) {
            main_logonUser.setText("当前用户:" + user.getName() + " - " + user.getDepartName());
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        mShakeListener.stop();
        super.onDestroy();

        //Intent i = new Intent(Intent.ACTION_CALL);
        //i.setData(Uri.parse(DISABLE_SERVICE));
        //startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    class TeleListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
            case TelephonyManager.CALL_STATE_IDLE: {
                Logger.d("CALL_STATE_IDLE");
                //main_message_count.setText("CALL_STATE_IDLE ");
                showText(main_message_count, "CALL_STATE_IDLE");
                break;
            }
            case TelephonyManager.CALL_STATE_OFFHOOK: {
                Logger.d("CALL_STATE_OFFHOOK");
                //main_message_count.setText("CALL_STATE_OFFHOOK");
                showText(main_message_count, "CALL_STATE_OFFHOOK");
                break;
            }
            case TelephonyManager.CALL_STATE_RINGING: {
                Logger.d("CALL_STATE_RINGING");
                //main_message_count.setText("CALL_STATE_RINGING");
                showText(main_message_count, "CALL_STATE_RINGING");
                try {
                    iTelephony.endCall();
                } catch (RemoteException e1) {
                    Logger.e(e1.getMessage());
                    e1.printStackTrace();
                }
                break;
            }
            default:
                break;
            }
        }
    }

    void showText(TextView view, String msg) {
        main_message_count.setVisibility(View.VISIBLE);
        main_message_count.setText(msg);
        showTextInfo getData = new showTextInfo(showTextHandler);
        Thread thread = new Thread(getData);
        thread.start();
    }

    //
    class showTextInfo implements Runnable {
        Handler handler;

        public showTextInfo(Handler h) {
            this.handler = h;
        }

        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = Message.obtain();
            Bundle bundle = new Bundle();
            bundle.putInt("type", 1);
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    //回调函数，显示结果
    Handler showTextHandler = new Handler() {
        public void handleMessage(Message msg) {
            int type = msg.getData().getInt("type");
            switch (type) {
            case 1: {
                main_message_count.setVisibility(View.INVISIBLE);
                main_message_count.setText("");

                break;
            }
            case 0: {
                break;
            }
            default: {
            }
            }
        }
    };

    void sensorInit() {
        mShakeListener = new ShakeListener(this);
        mShakeListener.setOnShakeListener(new OnShakeListener() {

            public void onShake() {
                ToastUtils.show(MainActivity.this, "摇了一下...");
            }
        });
    }
}
