package com.app.example;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.app.example.adapter.HzCursorAdapter;
import com.ek.mobileapp.MainApplication;
import com.ek.mobileapp.dao.HzDataHelper;
import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.Logger;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.volley.JsonRequest;
import com.ek.mobilebapp.R;

public class HzCursorListActivity extends FragmentActivity implements OnScrollListener,
        LoaderManager.LoaderCallbacks<Cursor> {
    static final String HZS_LIST = "/common/get_hzs?page=%1$d";
    static final String HZS_PAGES = "/common/get_hzs_pages";

    static final int STATE_LOADING = 0X01;
    static final int STATE_END = 0X09;

    //数据装载状态
    int state = STATE_END;

    TextView title;
    ListView mListView;
    int mPage = 1;
    int pages = 1;
    HzCursorAdapter mAdapter;
    TextView msg;

    MainApplication ma;
    HzDataHelper mDataHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.hz_cursor_lists);

        ma = (MainApplication) getApplication();
        title = (TextView) findViewById(R.id.custom_title_label);
        title.setText("Volley...");

        mListView = (ListView) findViewById(R.id.listView);
        mDataHelper = new HzDataHelper(MainApplication.get().getApplicationContext());

        //
        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter = new HzCursorAdapter(this, mListView);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(this);

        msg = (TextView) findViewById(R.id.main_message_count);
        getPages();

        getSupportLoaderManager().initLoader(0, null, HzCursorListActivity.this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (this.mPage == this.pages || state == STATE_LOADING)
            return;

        if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0
                && totalItemCount != mListView.getHeaderViewsCount() + mListView.getFooterViewsCount()
                && mAdapter.getCount() > 0) {
            loadNextPage();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //mDataHelper.deleteAll();

        return mDataHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.changeCursor(data);
        if (data != null && data.getCount() == 0) {
            loadFirstPage();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }

    private void loadFirstPage() {
        mPage = 1;
        loadData(mPage);
    }

    private void loadNextPage() {
        mPage++;
        loadData(mPage);
    }

    void loadData(final int page) {
        state = STATE_LOADING;

        msg.setText("");
        msg.setVisibility(View.VISIBLE);
        msg.setText("取数中..." + mPage + "");

        Logger.d("load:" + page + "");

        String url = "http://" + ma.getHost() + HZS_LIST;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(new JsonRequest<Hz.HzsRequestData>(String.format(url, page), Hz.HzsRequestData.class, null,
                new Response.Listener<Hz.HzsRequestData>() {

                    @Override
                    public void onResponse(Hz.HzsRequestData requestData) {
                        Logger.d("Response=>" + requestData.getTimes());

                        //int p = requestData.getPages();
                        //if (p != 0)
                        //    pages = p;

                        //mPage = requestData.getPage();
                        if (mPage == 1) {
                            Logger.d("hzs delete all...");
                            mDataHelper.deleteAll();
                        }

                        ArrayList<Hz> hzs = requestData.getHzs();
                        if (hzs != null) {
                            mDataHelper.bulkInsert(hzs);
                        }
                        msg.setVisibility(View.GONE);
                        state = STATE_END;
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError verror) {
                        ToastUtils.show(HzCursorListActivity.this, "取数失败^^");
                        Logger.e(verror.toString());

                        state = STATE_END;
                    }
                }));
    }

    void getPages() {
        String url = "http://" + ma.getHost() + HZS_PAGES;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(new JsonRequest<Hz.HzsRequestPagesData>(url, Hz.HzsRequestPagesData.class, null,
                new Response.Listener<Hz.HzsRequestPagesData>() {

                    @Override
                    public void onResponse(Hz.HzsRequestPagesData requestData) {
                        Logger.d("Response=>" + requestData.getTimes());

                        int p = requestData.getPages();
                        if (p != 0)
                            pages = p;
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError verror) {
                        ToastUtils.show(HzCursorListActivity.this, "取数失败^^");
                        Logger.e(verror.toString());

                    }
                }));
    }

}