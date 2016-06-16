package com.rs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rs.house1.R;

import java.util.ArrayList;

public class NavDrawerListAdapter extends BaseAdapter {
    public Context context;
    public ArrayList<NavDrawerItem> navDrawerItems;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder = null;
    public static class ViewHolder {
        public TextView title;
        // TextView txtView3;
        public ImageView imgIcon;
        // ImageButton imgBtn;

    }

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Toast.makeText(mContext,
            // "if condition",Toast.LENGTH_SHORT).show();

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(
                    R.layout.custom_list_element, parent, false);

        } else {
            // Toast.makeText(mContext,
            // "else condition",Toast.LENGTH_SHORT).show();

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title = (TextView) convertView
                .findViewById(R.id.title);
        viewHolder.imgIcon = (ImageView) convertView
                .findViewById(R.id.icon);
        viewHolder.imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        viewHolder.title.setText(navDrawerItems.get(position).getTitle());
        convertView.setTag(viewHolder);
//         txtView=(TextView)holder.findViewById(R.id.customTitle);
//      ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
//        TextView txtTitle=(TextView)convertView.findViewById(R.id.title);
//
//        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
//        txtTitle.setText(navDrawerItems.get(0).getTitle());
        return convertView;
    }
}
