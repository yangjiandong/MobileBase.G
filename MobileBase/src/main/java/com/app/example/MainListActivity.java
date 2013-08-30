package com.app.example;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.app.MyAppResource;
import com.ek.mobileapp.utils.DialogFactory;
import com.ek.mobileapp.utils.DialogFactory.DialogCallbacks;
import com.ek.mobilebapp.R;

public class MainListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(MyAppResource.LayoutMainList);

        //update to more icon
        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(com.ek.mobilebapp.R.drawable.more_icon),
                null, null);

        TextView title = (TextView) findViewById(R.id.custom_title_label);
        title.setText(R.string.app_name);

        ArrayAdapter<Sample> adapter = new ArrayAdapter<Sample>(this, android.R.layout.simple_list_item_1);
        adapter.add(new Sample("后台任务", IgnitedAsyncTaskActivity.class));
        adapter.add(new Sample("分页列表", EndlessListActivity.class));
        adapter.add(new Sample("分页列表2", EndlessListActivity.class));
        adapter.add(new Sample("分页列表3", EndlessListActivity.class));
        adapter.add(new Sample("分页列表4", EndlessListActivity.class));
        adapter.add(new Sample("分页列表5", EndlessListActivity.class));
        adapter.add(new Sample("分页列表6", EndlessListActivity.class));
        adapter.add(new Sample("分页列表7", EndlessListActivity.class));
        adapter.add(new Sample("分页列表8", EndlessListActivity.class));
        adapter.add(new Sample("分页列表9", EndlessListActivity.class));
        adapter.add(new Sample("分页列表11", EndlessListActivity.class));


        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Sample sample = (Sample) getListAdapter().getItem(position);
        Intent intent = new Intent(this, sample.activityClass);
        startActivity(intent);
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
                //ApplicationConfig.getInstance().exit();
                finish();
            }

            @Override
            public void runCancel(DialogInterface dialog, int which) {

            }
        };

        Dialog dialog = DialogFactory.create(this, "确认退出", "", dCalls);
        dialog.show();
    }

    private static final class Sample {
        public Sample(String title, Class<?> activityClass) {
            this.title = title;
            this.activityClass = activityClass;
        }

        private String title;
        private Class<?> activityClass;

        @Override
        public String toString() {
            return title;
        }
    }
}
