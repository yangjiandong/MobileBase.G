package com.app.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ek.mobileapp.model.Hz;
import com.ek.mobileapp.utils.BitmapManager;
import com.ek.mobileapp.utils.ToastUtils;
import com.ek.mobileapp.utils.ViewUtils;
import com.ek.mobilebapp.R;

public class HzCursorAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;
    private ListView mListView;
    private BitmapManager bmpManager;

    public HzCursorAdapter(Context context, ListView listView) {
        super(context, null, false);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        mListView = listView;
        this.bmpManager = new BitmapManager(BitmapFactory.decodeResource(context.getResources(),
                R.drawable.widget_dface_loading));
    }

    @Override
    public Hz getItem(int position) {
        mCursor.moveToPosition(position);
        return Hz.fromCursor(mCursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mLayoutInflater.inflate(R.layout.hz_cursor_item, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Holder holder = getHolder(view);

        view.setEnabled(!mListView.isItemChecked(cursor.getPosition() + mListView.getHeaderViewsCount()));

        Hz entity = Hz.fromCursor(cursor);
        //url
        String url = "1";//item.getHz();
        if (com.ek.mobileapp.utils.StringUtils.isEmpty(url)) {
            holder.image.setImageResource(R.drawable.widget_dface);
        } else {
            try {
                bmpManager.loadBitmap(url, holder.image);
            } catch (Exception e) {
                ViewUtils.saveErrorLogFromMyPackageName(e);
            }
        }

        holder.title.setText(entity.getHz());
        holder.userName.setText(entity.getHz());
        holder.text_view_count.setText(entity.getPy());
        holder.text_like_count.setText(String.valueOf(entity.getId()));
        holder.text_comment_count.setText(entity.getWb());
        holder.time.setText(entity.getCreated_at());//TimeUtils.getListTime(shot.getCreated_at()));

        holder.image.setTag(entity);
        holder.image.setOnClickListener(faceClickListener);
    }

    View.OnClickListener faceClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Hz entity = (Hz) v.getTag();

            ToastUtils.show(v.getContext(), entity.getHz() + " - " + entity.getCreated_at());

            //Intent intent = new Intent(Context, UserCenter.class);
            //intent.putExtra("his_id", hisuid);
            //intent.putExtra("his_name", hisname);
            //context.startActivity(intent);

            //UIHelper.showUserCenter(v.getContext(), post.getAuthorId(), post.getAuthor());
        }
    };

    private Holder getHolder(final View view) {
        Holder holder = (Holder) view.getTag();
        if (holder == null) {
            holder = new Holder(view);
            view.setTag(holder);
        }
        return holder;
    }

    private class Holder {
        public ImageView image;
        public ImageView avatar;
        public TextView title;
        public TextView userName;
        public TextView text_view_count;
        public TextView text_comment_count;
        public TextView text_like_count;
        public TextView time;

        public Holder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            title = (TextView) view.findViewById(R.id.title);
            userName = (TextView) view.findViewById(R.id.userName);
            text_view_count = (TextView) view.findViewById(R.id.text_view_count);
            text_comment_count = (TextView) view.findViewById(R.id.text_comment_count);
            text_like_count = (TextView) view.findViewById(R.id.text_like_count);
            time = (TextView) view.findViewById(R.id.time);
        }
    }
}
