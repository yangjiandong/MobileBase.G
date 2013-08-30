package com.app.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.app.example.adapter.HzAdapter;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.Utils;
import com.ek.mobilebapp.R;
import com.github.ignition.core.tasks.IgnitedAsyncTask;

//采用ignition 分页机制
public class HzListActivity extends ListActivity implements OnScrollListener {
    int current_page = 1;
    int pages = 1;

    HzAdapter adapter;
    TextView msg;

    MainApplication ma;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.hz_lists);

        ma = (MainApplication) getApplication();
        //
        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new HzAdapter(this);
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);

        msg = (TextView) findViewById(R.id.main_message_count);

        loadNextPage();
    }

    private void loadNextPage() {
        Logger.d("current page:" + current_page);

        //if (current_page > 1 && current_page > pages) {
        //    return;
        //}
        msg.setText("");
        msg.setVisibility(View.VISIBLE);
        msg.setText(current_page + "");

        adapter.setIsLoadingData(true);
        IgnitedAsyncTask<HzListActivity, Void, Void, Void> task = new IgnitedAsyncTask<HzListActivity, Void, Void, Void>() {

            @Override
            public Void run(Void... pams) throws Exception {
                //getHzs();
                List<Hz> alls = ma.getHzList(current_page, false);

                for (Hz hz : alls) {
                    adapter.getData().add(hz);
                }
                return null;
            }

            //直接后台取数
            private void getHzs() throws JSONException {
                String ip = Utils.readHost(R.raw.hostdev, HzListActivity.this).toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("page", current_page + ""));
                JSONObject res = HttpTool.getTool().post("http://" + ip + "/common/get_hzs", params);

                Logger.d("times:" + res.getInt("times"));
                int ps = res.getInt("pages");
                Logger.d("pages:" + ps);
                if (ps != 0) {
                    pages = ps;
                }

                JSONArray arrays = res.getJSONArray("hzs");
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject p = (JSONObject) arrays.get(i);
                    adapter.getData().add(JSON.parseObject(p.toString(), Hz.class));
                }
            }

            @Override
            public boolean onTaskCompleted(Void result) {
                current_page++;

                msg.setText("");
                msg.setVisibility(View.GONE);

                adapter.setIsLoadingData(false);
                adapter.notifyDataSetChanged();
                return true;
            }

        };
        task.execute();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (adapter.shouldRequestNextPage(firstVisibleItem, visibleItemCount, totalItemCount)) {
            loadNextPage();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
}