package com.app.example.adapter;

import android.app.ListActivity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.BitmapManager;
import com.ek.mobileapp.utils.StringUtils;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobilebapp.R;
import com.github.ignition.core.adapters.EndlessListAdapter;

public class HzAdapter extends EndlessListAdapter<Hz> {

    private BitmapManager bmpManager;

    static class ListItemView { //自定义控件集合
        public ImageView face;
        public TextView title;
        public TextView author;
        public TextView date;
        public TextView count;
    }

    public HzAdapter(ListActivity activity) {
        super(activity, R.layout.loading_item);
        this.bmpManager = new BitmapManager(BitmapFactory.decodeResource(activity.getResources(), R.drawable.widget_dface_loading));
    }

    @Override
    protected View doGetView(int position, View convertView, ViewGroup parentView) {
        ListItemView listItemView = null;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getListView().getContext());
            convertView = inflater.inflate(R.layout.hz_item, null);

            listItemView = new ListItemView();
            listItemView.face = (ImageView)convertView.findViewById(R.id.listitem_userface);
            listItemView.title = (TextView) convertView.findViewById(R.id.listitem_title);
            listItemView.author = (TextView) convertView.findViewById(R.id.listitem_author);
            listItemView.date = (TextView) convertView.findViewById(R.id.listitem_date);
            listItemView.count = (TextView) convertView.findViewById(R.id.listitem_count);

            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }

        Hz item = getItem(position);

        //url
        String url = "1";//item.getHz();
        if (com.ek.mobileapp.utils.StringUtils.isEmpty(url)) {
            listItemView.face.setImageResource(R.drawable.widget_dface);
        } else {
            try {
                bmpManager.loadBitmap(url, listItemView.face);
            } catch (Exception e) {
                ViewUtils.saveErrorLogFromMyPackageName(e);
            }
        }
        listItemView.face.setOnClickListener(faceClickListener);
        listItemView.face.setTag(item);//带上显示信息

        listItemView.title.setText(item.getHz() + " - 拼音 - " + item.getPy() + " - wb " + item.getWb());
        listItemView.title.setTag(item);
        listItemView.author.setText(item.getPy());
        listItemView.date.setText(StringUtils.friendly_time("2013-07-01 00:00:01"));
        listItemView.count.setText(item.getId() + "");

        return convertView;
    }

    private View.OnClickListener faceClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Hz post = (Hz) v.getTag();

            ToastUtils.show(v.getContext(), post.getHz());

            //Intent intent = new Intent(Context, UserCenter.class);
            //intent.putExtra("his_id", hisuid);
            //intent.putExtra("his_name", hisname);
            //context.startActivity(intent);

            //UIHelper.showUserCenter(v.getContext(), post.getAuthorId(), post.getAuthor());
        }
    };
}
