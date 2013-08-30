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
import com.ek.mobileapp.model.UserDTO;
import com.ek.mobileapp.utils.GlobalCache;
import com.ek.mobilebapp.R;

//登录用户列表
public class UserListActivity extends Activity {
    MainApplication ac;

    // Intent request codes
    public static final int REQUEST_USER_SET = 1;

    // Debugging
    private static final String TAG = "UserListActivity";
    private static final boolean D = true;

    private ArrayAdapter<String> mUsersArrayAdapter;
    HashMap<String, UserDTO> usrs;

    // Return Intent extra
    public static String EXTRA_USER_LOGINNAME = "login_name";
    public static String EXTRA_USER_PWD = "user_pwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_list);

        ac = (MainApplication) getApplication();

        // Set result CANCELED incase the user backs out
        setResult(Activity.RESULT_CANCELED);

        String as = ac.getUserList();
        List<UserDTO> barList1 = JSON.parseArray(as, UserDTO.class);

        mUsersArrayAdapter = new ArrayAdapter<String>(this, R.layout.user_name);
        usrs = new HashMap<String, UserDTO>();

        if (GlobalCache.getCache().getLoginuser() != null) {
            TextView currentUserLabel = (TextView) findViewById(R.id.user_list_currentUserLabel);
            currentUserLabel.setVisibility(View.VISIBLE);

            TextView currentUser = (TextView) findViewById(R.id.user_list_currentUser);
            currentUser.setVisibility(View.VISIBLE);
            currentUser.setText(GlobalCache.getCache().getLoginuser().getName());
        }

        ListView listUser = (ListView) findViewById(R.id.user_list);
        for (UserDTO u : barList1) {
            findViewById(R.id.title_user_list).setVisibility(View.VISIBLE);
            String s = u.getName() + " - " + u.getLoginName();
            usrs.put(s, u);
            mUsersArrayAdapter.add(s);
        }
        listUser.setAdapter(mUsersArrayAdapter);
        listUser.setOnItemClickListener(mDeviceClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {

            String info = ((TextView) v).getText().toString();
            UserDTO u = usrs.get(info);

            Intent intent = new Intent();
            intent.putExtra(EXTRA_USER_LOGINNAME, u.getLoginName());
            intent.putExtra(EXTRA_USER_PWD, u.getPassword());

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    };

}
