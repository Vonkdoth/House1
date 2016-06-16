package com.rs.house1;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by hp on 5/3/2016.
 */
public class CustomAdapterTab3 extends ArrayAdapter<String> {

    String[] prgmNameList;
    String[] address;
    String[] midammount;
    Context context;
    ImageView delete;
    ArrayList<Tab3ChangePojo> tab3ChangePojos;
    ArrayList<String> arrayListdata;
    String response = "x";


    public CustomAdapterTab3(Context context, String prgmNameList[], String address[], String midammount[], ArrayList<Tab3ChangePojo> tab3ChangePojos) {
//for (int i=0;i<tab3ChangePojos.size();i++){
//
//}
        super(context, R.layout.layout_list_tab3, prgmNameList);
        this.prgmNameList = prgmNameList;
        this.address = address;
        this.midammount = midammount;
        this.context = context;
        this.tab3ChangePojos = tab3ChangePojos;


    }

    static class ViewHolder {

        TextView prgmName, address, miammount;
        ImageView delete;
        Button button, button1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {


            LayoutInflater inflater = ((Activity) context).getLayoutInflater();

            convertView = inflater.inflate(R.layout.layout_list_tab3, parent, false);
            holder = new ViewHolder();
            holder.prgmName = (TextView) convertView.findViewById(R.id.txtName);
            holder.address = (TextView) convertView.findViewById(R.id.txtLocation);
            holder.miammount = (TextView) convertView.findViewById(R.id.bidammount);
            holder.delete=(ImageView) convertView.findViewById(R.id.delete);


//            holder.button = (Button) convertView.findViewById(R.id.btn1);
//            holder.button1 = (Button) convertView.findViewById(R.id.btn2);
            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        try {
            final Tab3ChangePojo searchServiceProviderPojo = tab3ChangePojos.get(position);

            String name = searchServiceProviderPojo.getName();
            String customer_id = searchServiceProviderPojo.getCustomer_id();
            // String mobile = searchServiceProviderPojo.getMobile();
            final String rating = searchServiceProviderPojo.getRating();
            final String request_id=searchServiceProviderPojo.getRequest_id();
            // final String longi = searchServiceProviderPojo.getLongi();

            holder.prgmName.setText(name);

            holder.address.setText(customer_id);

            holder.miammount.setText(String.format("%.1f ",Float.valueOf(rating)));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWebService1(request_id);
            }
        });}
        catch (Exception e){
            e.printStackTrace();

        }return convertView;

    }
    public void callWebService1(final String request_id) {
        new AsyncTask<Void, Void, String>() {


            @Override
            protected void onPostExecute(String jsonData) {


                super.onPostExecute(jsonData);
                Toast.makeText(context,"Please Refresh The Tab For Delete The Order",Toast.LENGTH_LONG).show();
                Log.d("Response is",response);
                notifyDataSetChanged();
               // Toast.makeText(context, "" + response, Toast.LENGTH_LONG).show();
               // orderFragmentPojoArrayList=new ArrayList<OrderFragmentPojo>();
                try {
//                    JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("message");
//                    for (int i=0;i<jsonArray.length();i++) {
//                        JSONObject obj = jsonArray.getJSONObject(i);
                        //sobj.getString("")
                        //OrderFragmentPojo orderFragmentPojo=new OrderFragmentPojo();
//                        "order_id": 10,
//                                "id": 10,
//                                "provider_id": 171,
//                                "name": "qwerty",
//                                "mobile": 425652846,
//                                "lati": 22.7359725,
//                                "longi": 75.8977884,
//                                "service_name": "Carpenter",
//                                "date_added": "2016-05-15"
//                        orderFragmentPojo.setOrder_id(obj.getString("order_id"));
//                        orderFragmentPojo.setId(obj.getString("id"));
//                        orderFragmentPojo.setProvider_id(obj.getString("provider_id"));
//                        orderFragmentPojo.setName(obj.getString("name"));
//                        orderFragmentPojo.setMobile(obj.getString("mobile"));
//                        orderFragmentPojo.setLati(obj.getString("lati"));
//                        orderFragmentPojo.setLongi(obj.getString("longi"));
//                        orderFragmentPojo.setService_name(obj.getString("service_name"));
//                        orderFragmentPojo.setDate_added(obj.getString("date_added"));
//                        orderFragmentPojoArrayList.add(orderFragmentPojo);

//                    }
                    // JSONObject jsonObject = new JSONObject(jsonData.toString());
                    Log.d("New Json data", jsonData.toString());
//                    adapter = new ServiceAdapter(getActivity().getApplicationContext(),orderFragmentPojoArrayList,"");
//                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("acceptstatus" + pro_id, "true");
//            editor.putString("name" + pro_id, serviceProviderPojoArrayList.get(listPosition).getName());
//            editor.putString("category_name" + pro_id, serviceProviderPojoArrayList.get(listPosition).getCategory_name());
//            editor.putString("rating" + pro_id, serviceProviderPojoArrayList.get(listPosition).getRating());
//            editor.putString("distance" + pro_id, serviceProviderPojoArrayList.get(listPosition).getDistance().toString());
//            editor.putString("profile_image" + pro_id, serviceProviderPojoArrayList.get(listPosition).getProfile_image().toString());
//            editor.putString("provider_id" + pro_id, serviceProviderPojoArrayList.get(listPosition).getId().toString());
//            editor.putString("id", id);
//            // editor.putString("order_id"+pro_id,serviceProviderPojoArrayList.get(listPosition).getOrderid().toString());
//            editor.putString("contact" + pro_id, serviceProviderPojoArrayList.get(listPosition).getContact_no());
//            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            String currentDateTime = df.format(date);
//            Log.d("total Date", currentDateTime);
//            String currentDate = currentDateTime.substring(0, 11);
//            Log.d("current Date", currentDate);
//            String currentTime = currentDateTime.substring(11, 19);
//            Log.d("currentTime", currentTime);
//            // Log.d("order_id", serviceProviderPojoArrayList.get(listPosition).getOrderid().toString());
//            editor.putString("date" + pro_id, currentDate);
//            editor.putString("time" + pro_id, currentTime);
//            editor.commit();


            }

            @Override
            protected String doInBackground(Void... params) {
               // String request = "http://sabkuchhai.in/home/api/v1/index.php/deleteCompleteRequest";

                String request = "http://www.house1.in/app/api/v1/index.php/deleteCompleteRequest";
                try {

                    java.net.URL url = new URL(request);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");
                    connection.setUseCaches(false);

                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

                    JSONObject jsonParam = new JSONObject();
//                            jsonParam.put("ID", "25");
//                            jsonParam.put("description", "Real");
//                            jsonParam.put("enable", "true");
//                            {
//                                "customer_id":"1",
//                                    "provider_id":"127",
//                                    "service_name":"Electrician",
//                                    "lati":"",
//                                    "longi":""
//                            }
//                    sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("loginpref", getActivity().getApplicationContext().MODE_PRIVATE);
//                    // editor.putString("id", id);
//                    String id = sharedPreferences.getString("id", "");
                    jsonParam.put("request_id", request_id);
//                    jsonParam.put("provider_id", serviceProviderPojoArrayList.get(listPosition).getId().toString());
//                    jsonParam.put("service_name", serviceProviderPojoArrayList.get(listPosition).getCategory_name());
//                    jsonParam.put("lati", latitude);
//                    jsonParam.put("longi", longitude);
//                    Log.d("latitude", latitude);
//                    Log.d("Longitude", longitude);
                   // Log.d("N User id", id);
//                    Log.d("Service Name", serviceProviderPojoArrayList.get(listPosition).getCategory_name());
//                    // jsonParam.put("password", temppassword);
//                    Log.d("Latest Pro id", serviceProviderPojoArrayList.get(listPosition).getId().toString());
                    wr.writeBytes(jsonParam.toString());

                    wr.flush();
                    wr.close();
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        String line = "";
                        BufferedReader br = null;
                        response = "";
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                        }
                    } else {
                        response = "";
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                return response;
            }
        }.execute();

    }
}
