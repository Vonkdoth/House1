package com.rs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rs.house1.R;
import com.rs.model.Post;

import java.util.ArrayList;

/**
 * Created by richasaxena on 27/04/16.
 */
public class PostAcceptedAdapter extends BaseAdapter {
    Context context;
    ArrayList<Post> arrayList;
    public PostAcceptedAdapter(Context context, ArrayList<Post> arrayList){

        this.context=context;
        this.arrayList=arrayList;

    }
    @Override
    public int getCount() {

        if(arrayList==null)
            return 0;
        else
            return arrayList.size();
       // return arrayList.size();
    }

    @Override
    public Post getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_new_order,null);
        TextView txtName= (TextView) convertView.findViewById(R.id.txtName);
        TextView txtAddress=(TextView) convertView.findViewById(R.id.txtLocation);
        txtName.setText(arrayList.get(position).getUserName());
        txtAddress.setText(arrayList.get(position).getAddress());
        return convertView;
    }
}
