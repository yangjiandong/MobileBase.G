package com.app.example.adapter;

import android.view.LayoutInflater;
import android.view.View;

import com.ek.mobileapp.model.Hz;
import com.ek.mobilebapp.R.id;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class HzListAdapter extends SingleTypeAdapter<Hz> {

    private int selectItem = -1;

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    public int getSelectItem() {
        return this.selectItem;
    }

    public HzListAdapter(final LayoutInflater inflater, final Hz[] elements, int id) {
        super(inflater, id);
        setItems(elements);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[] {id.text};
    }

    @Override
    protected void update(final int position, final View view, final Hz data) {
        setCurrentView(view);
        update(position, data);
    }

    protected void update(final int position, final Hz data) {

    }
}