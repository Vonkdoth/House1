package com.rs.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rs.house1.R;

/**
 * Created by hp on 5/2/2016.
 */
public class CustomAdapterTab1 extends ArrayAdapter<String> {
    String[] prgmNameList;
    String[] address;
    String[] midammount;
    Context context;

    public CustomAdapterTab1(Context context, String prgmNameList[], String address[], String midammount[]) {

        super(context, R.layout.layout_list_tab1, prgmNameList);
        this.prgmNameList = prgmNameList;
        this.address = address;
        this.midammount = midammount;
this.context=context;

    }

    static class ViewHolder {

        TextView prgmName, address, miammount;
        Button button, button1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.layout_list_tab1, parent, false);
            holder = new ViewHolder();
            holder.prgmName = (TextView) convertView.findViewById(R.id.txtName);
            holder.address = (TextView) convertView.findViewById(R.id.txtLocation);
            holder.miammount = (TextView) convertView.findViewById(R.id.bidammount);

            holder.button = (Button) convertView.findViewById(R.id.btn1);
            holder.button1 = (Button) convertView.findViewById(R.id.btn2);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.prgmName.setText(prgmNameList[position]);

        holder.address.setText(address[position]);

        holder.miammount.setText(midammount[position]);
return convertView;
    }
}