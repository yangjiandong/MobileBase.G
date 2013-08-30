package com.app.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.app.example.adapter.LetterlistAdapter;
import com.app.example.dao.ContentDAO;
import com.app.example.model.Content;
import com.ek.mobilebapp.R;
import com.github.kevinsawicki.wishlist.AsyncLoader;

public class LetterListActivity extends FragmentActivity implements LoaderCallbacks<List<Content>> {
    String TAG = getClass().getSimpleName();
    final int LoaderManagerID = 112;

    LetterlistAdapter adapter;
    ListView mListView;
    private LetterSideBar indexBar;
    private WindowManager mWindowManager;
    private TextView mDialogText;
    private View head;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.letterlist_layout);

        //
        Button leftBtn = (Button) findViewById(com.ek.mobilebapp.R.id.custom_title_btn_left);
        leftBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mListView = (ListView) this.findViewById(R.id.list);
        indexBar = (LetterSideBar) findViewById(R.id.sideBar);
        mDialogText = (TextView) LayoutInflater.from(this).inflate(R.layout.letterlist_list_position, null);
        head = LayoutInflater.from(this).inflate(R.layout.letterlist_head, null);
        mListView.addHeaderView(head);
        adapter = new LetterlistAdapter(this, null);

        mDialogText.setVisibility(View.INVISIBLE);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        indexBar.setTextView(mDialogText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LoaderManagerID, null, this);
    }

    Loader<List<Content>> createList() {
        return new AsyncLoader<List<Content>>(this) {
            @Override
            public List<Content> loadInBackground() {
                return getItemsbyPatient();
            }
        };
    }

    synchronized private List<Content> getItemsbyPatient() {
        Log.d(TAG, "开始取数...");

        ContentDAO dao = null;
        //初始化数据
        List<Content> list = new ArrayList<Content>();
        try {
            dao = new ContentDAO(this);
            if (dao.count() == 0) {
                for (int i = 0; i < 100; i++) {
                    Content m;
                    if (i < 23)
                        m = new Content("A", "选项" + i);
                    else if (i < 66)
                        m = new Content("F", "选项" + i);
                    else
                        m = new Content("D", "选项" + i);
                    //list.add(m);
                    dao.save(m);
                }
            }

            list = dao.findAllContent();
        } catch (Exception e) {
            com.ek.mobileapp.utils.Logger.e(e.getMessage());
        } finally {
            dao.close();
        }

        //根据a-z进行排序
        Collections.sort(list, new com.app.example.model.PinyinComparator());

        return list;
    }

    @Override
    public Loader<List<Content>> onCreateLoader(int id, Bundle args) {
        return createList();
    }

    @Override
    public void onLoadFinished(Loader<List<Content>> loader, List<Content> data) {
        adapter.setList(data);
        mListView.setAdapter(adapter);
        //设置SideBar的ListView内容实现点击a-z中任意一个进行定位
        indexBar.setListView(mListView);
    }

    @Override
    public void onLoaderReset(Loader<List<Content>> loader) {
        adapter.setList(null);
    }

}
