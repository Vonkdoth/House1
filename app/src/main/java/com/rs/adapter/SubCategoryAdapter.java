package com.rs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rs.house1.R;
import com.rs.model.Category;
import com.rs.model.SubCategory;

import java.util.ArrayList;

/**
 * Created by richasaxena on 12/04/16.
 */
public class SubCategoryAdapter extends BaseAdapter {

    Context context;
    int res;
    ArrayList<SubCategory> arrayList;

    public SubCategoryAdapter(Context context, int res, ArrayList<SubCategory> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
        this.res=res;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.row_spin,null);
        TextView txt= (TextView) convertView.findViewById(R.id.txtSpinner);
        txt.setText(arrayList.get(position).getSubCategoryName());
        return convertView;
    }
}
