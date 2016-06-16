package com.rs.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rs.house1.MyMap;
import com.rs.house1.R;
import com.rs.house1.Tab2ChangePojo;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hp on 5/2/2016.
 */
public class CustomerAdapterTab2 extends ArrayAdapter<String> {

    String[] prgmNameList;
    String[] address;
    String[] midammount;
    Context context;
    ArrayList<Tab2ChangePojo> tab2ChangePojos;
    SharedPreferences sharedPreferences;

    public CustomerAdapterTab2(Context context, String prgmNameList[], String address[],String[] midammount,ArrayList<Tab2ChangePojo> tab2ChangePojos) {

        super(context, R.layout.layout_list_tab2, prgmNameList);
        this.prgmNameList = prgmNameList;
        this.address = address;
        this.midammount = midammount;
        this.tab2ChangePojos=tab2ChangePojos;
        this.context=context;

    }

    static class ViewHolder {

        TextView prgmName, address, miammount;
        ImageView imageView, imageView1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.layout_list_tab2, parent, false);
            holder = new ViewHolder();
            holder.prgmName = (TextView) convertView.findViewById(R.id.txtName);
            //  holder.address = (TextView) convertView.findViewById(R.id.txtLocation);
            holder.miammount = (TextView) convertView.findViewById(R.id.txt_service);

            holder.imageView = (ImageView) convertView.findViewById(R.id.location_map);
            holder.imageView1 = (ImageView) convertView.findViewById(R.id.call);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        try {
            final Tab2ChangePojo searchServiceProviderPojo = tab2ChangePojos.get(position);

            String service = searchServiceProviderPojo.getService_name();
            String name = searchServiceProviderPojo.getName();
           // String mobile = searchServiceProviderPojo.getMobile();
            final String lati = searchServiceProviderPojo.getLati();
            final String longi = searchServiceProviderPojo.getLongi();
//        searchServiceProviderPojo.setService_name(providerInfoObject.getString("service_name"));
//        searchServiceProviderPojo.setDate_added(providerInfoObject.getString("date_added"));
//


            holder.prgmName.setText(name);
            // holder.address.setText(service);

            holder.miammount.setText(service);
            Log.d("Name",name);
            Log.d("Service name",service);
            Log.d("latitudecustom",lati);
            Log.d("longotudecustom",longi);

            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makeCall(searchServiceProviderPojo.getMobile().toString());
                }
            });

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    sharedPreferences=context.getSharedPreferences("location",context.MODE_PRIVATE);
//                    String newlat=sharedPreferences.getString("lat","");
//                    String newlong=sharedPreferences.getString("lng","");
                   Intent intent = new Intent(context.getApplicationContext(), MyMap.class);
//                    intent.putExtra("lati", newlat);
//                 intent.putExtra("longi", newlong);

                    intent.putExtra("lati", lati);
                    intent.putExtra("longi", longi);
                    Log.d("latitude latest",lati);
                    Log.d("longitude latest",longi);
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }            return convertView;

    }
    protected void makeCall(String contactNo) {
//        Log.i("make call", "");

        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + contactNo));
        Log.d("Contact no",contactNo);
        phoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(phoneIntent);


        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


}
