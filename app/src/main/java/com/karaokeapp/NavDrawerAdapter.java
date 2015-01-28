package com.karaokeapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavDrawerAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<NavDrawerItem> mNavigationDrawerItems;

    public NavDrawerAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.mContext = context;
        this.mNavigationDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return mNavigationDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavigationDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {

        public ImageView icon;

        public TextView title;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater mInflater = ((Activity) mContext).getLayoutInflater();
            convertView = mInflater.inflate(R.layout.item_navigation_drawer_list, parent, false);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_navigation_title);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_navigation_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mNavigationDrawerItems.get(position).getTitle());
        holder.icon.setImageResource(mNavigationDrawerItems.get(position).getIcon());
        return convertView;
    }
}
