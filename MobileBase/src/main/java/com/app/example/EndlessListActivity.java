package com.app.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.example.adapter.InfinityAdapter;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.HttpTool;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.Utils;
import com.ek.mobilebapp.R;
import com.github.ignition.core.tasks.IgnitedAsyncTask;
import com.github.kevinsawicki.wishlist.ActivityUtils;

//采用ignition 分页机制
public class EndlessListActivity extends ListActivity implements OnScrollListener {

    static final int PAGE_SIZE = 10;
    int page = 0;

    InfinityAdapter adapter;
    TextView msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.endless_lists_sample);

        //
        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
                //ActivityUtils.goHome(EndlessListActivity.this, MainActivity.class);
            }
        });

        adapter = new InfinityAdapter(this);
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);

        msg = (TextView) findViewById(R.id.main_message_count);

        loadNextPage();
    }

    private void loadNextPage() {
        this.page++;

        adapter.setIsLoadingData(true);

        //volley
        RequestQueue queue = Volley.newRequestQueue(this);
        String ip = ((MainApplication)getApplication()).getHost();
        String url = "http://" + ip + "/common/get_hzs?page=" + page;
        JsonObjectRequest jsobjRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Logger.d("Response=>" + response.toString());

                        try {
                            JSONArray arrays = response.getJSONArray("hzs");//response.getJSONArray("hzs");
                            for (int i = 0; i < arrays.length(); i++) {
                                JSONObject p = (JSONObject) arrays.get(i);
                                adapter.getData().add(JSON.parseObject(p.toString(), Hz.class));
                            }

                            adapter.setIsLoadingData(false);
                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            Logger.e(e.toString());
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError verror) {
                        Logger.e(verror.toString());
                    }
                });
        queue.add(jsobjRequest);
        queue.start();

        //ignitedAsyncTask();
    }

    private void ignitedAsyncTask() {
        IgnitedAsyncTask<EndlessListActivity, Void, Void, Void> task = new IgnitedAsyncTask<EndlessListActivity, Void, Void, Void>() {
            @Override
            public Void run(Void... pams) throws Exception {

                String ip = Utils.readHost(R.raw.hostdev, EndlessListActivity.this).toString();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("page", page + ""));
                JSONObject res = HttpTool.getTool().post("http://" + ip + "/common/get_hzs", params);

                JSONArray arrays = res.getJSONArray("hzs");
                for (int i = 0; i < arrays.length(); i++) {
                    JSONObject p = (JSONObject) arrays.get(i);
                    adapter.getData().add(JSON.parseObject(p.toString(), Hz.class));
                }
                return null;
            }

            @Override
            public boolean onTaskCompleted(Void result) {
                //msg.setText("");
                //msg.setVisibility(View.GONE);

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