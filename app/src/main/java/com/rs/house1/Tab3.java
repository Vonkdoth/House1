package com.rs.house1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.rs.adapter.CustomerAdapterTab2;

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
public class Tab3 extends Fragment {

    ListView lv;
    Context context;
    String response = "x";

    ArrayList prgmName;
    //   public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static String[] prgmNameList = {"Shahil", "Akash", "Ashok", "Ashish", "Rahul"};
    public static String[] address = {"1", "2", "3", "4", "5"};
    public static String[] midammount = {"100", "200", "300", "400", "500"};


    public Tab3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      Log.d("On Create","");
        final View rootView = inflater.inflate(R.layout.tablist3, container, false);

        context = getActivity();
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPostExecute(String jsonData) {
                super.onPostExecute(jsonData);
//                {
//                    "customer_id": 12,
//                        "email": "gopimuralidhar@gmail.com ",
//                        "name": "Muralidhar",
//                        "mobile": 8886614903,
//                        "lati": "",
//                        "longi": "",
//                        "service_name": "q",
//                        "rating": 2
//                },

                //Toast.makeText(getActivity().getApplicationContext(), "" + jsonData, Toast.LENGTH_LONG).show();
                Log.d("response:fgregr", jsonData);

                try {
                    ArrayList<Tab3ChangePojo> searchServicePRoviderList = new ArrayList<Tab3ChangePojo>();

                    JSONObject jsonObject = new JSONObject(jsonData.toString());
                    JSONArray jsonArray = jsonObject.optJSONArray("message");
                    prgmNameList = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Tab3ChangePojo searchServiceProviderPojo = new Tab3ChangePojo();
                        JSONObject providerInfoObject = jsonArray.getJSONObject(i);
                        searchServiceProviderPojo.setCustomer_id(providerInfoObject.getString("customer_id"));
                        searchServiceProviderPojo.setEmail(providerInfoObject.getString("email"));
                        searchServiceProviderPojo.setName(providerInfoObject.getString("name"));
                        searchServiceProviderPojo.setMobile(providerInfoObject.getString("mobile"));
                        searchServiceProviderPojo.setLati(providerInfoObject.getString("lati"));
                        searchServiceProviderPojo.setLongi(providerInfoObject.getString("longi"));
                        searchServiceProviderPojo.setService_name(providerInfoObject.getString("service_name"));
                        searchServiceProviderPojo.setRating(providerInfoObject.getString("rating"));
                        searchServiceProviderPojo.setRequest_id(providerInfoObject.getString("request_id"));
                        searchServicePRoviderList.add(searchServiceProviderPojo);
                        prgmNameList[i] = providerInfoObject.getString("name");
                    }
//                    adapter1 = new CustomRecyleViewAdapter(CustomeTextView.this, searchServicePRoviderList,String.valueOf(latitude),String.valueOf(longitude));
//
//                    recyclerView.setAdapter(adapter1);

//                    CustomerAdapterTab2 adapter = new CustomerAdapterTab2(context, prgmNameList, address,midammount,searchServicePRoviderList);
//                    ListView listView = (ListView) rootView.findViewById(R.id.mobile_list1);
////        ListView listView1=(ListView)findViewById(R.id.image);
//
//                    listView.setAdapter(adapter);

                    CustomAdapterTab3 adapter = new CustomAdapterTab3(context, prgmNameList, address, midammount, searchServicePRoviderList);
                    ListView listView = (ListView) rootView.findViewById(R.id.mobile_list1);
//        ListView listView1=(ListView)findViewById(R.id.image);

                    listView.setAdapter(adapter);


                } catch (Exception e) {

                }


            }

            @Override
            protected String doInBackground(Void... params) {
                String request = "http://www.house1.in/app/api/v1/index.php/completedRequest";

//                String request = "http://sabkuchhai.in/home/api/v1/index.php/completedRequest";
                try {

                    URL url = new URL(request);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    //  connection.setInstanceFollowRedirects(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");
                    //connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                    connection.setUseCaches(false);

                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                    //  wr.writeBytes(otherParametersUrServiceNeed);

                    JSONObject jsonParam = new JSONObject();
//                            jsonParam.put("ID", "25");
//                            jsonParam.put("description", "Real");
//                            jsonParam.put("enable", "true");

                    // SharedPreferences sharedPreferences = context.getSharedPreferences("house1",context.MODE_PRIVATE);
                    //String tempUserId=sharedPreferences.getString("user_id","");
                    final int id = getActivity().getApplicationContext().getSharedPreferences("House1", Context.MODE_PRIVATE).getInt("id", 0);


                    jsonParam.put("service_user_id", String.valueOf(id));
                    Log.d("Tempuserid", String.valueOf(id));

                    //  Toast.makeText(context,""+tempUserId,Toast.LENGTH_LONG).show();

                    wr.writeBytes(jsonParam.toString());

                    wr.flush();
                    wr.close();
                    connection.connect();
                    response = "";
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = br.readLine()) != null) {
                            response += line;
                            Log.d("internal", "" + response);
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


        //context=this;


        return rootView;


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("on Resume","");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Pause","");
    }

}
