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

import com.rs.adapter.CustomAdapterTab1;
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
 * Created by hp on 5/2/2016.
 */
public class Tab2 extends Fragment {
    ListView lv;
    Context context;
    String response = "x";


    ArrayList prgmName;
    //   public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static String [] prgmNameList;
    public static String [] address={"1","2","3","4","5"};
    public static String [] midammount={"Plumbing", "Laundry", "Dry Cleanig", "Electrician", "Cleaning"};


    public Tab2(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      final   View rootView = inflater.inflate(R.layout.tablist2, container, false);
        //context=this;
        context=getActivity();
        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPostExecute(String jsonData) {
                super.onPostExecute(jsonData);
////                {
//                "customer_id": 1,
//                        "email": "ankitneem1aa@gmail.com",
//                        "name": "ankit",
//                        "mobile": 78282807844,
//                        "lati": "",
//                        "longi": "",
//                        "service_name": "p",
//                        "date_added": "2016-05-12"
//            }
//            ]
//        }
                try {
                    ArrayList<Tab2ChangePojo> searchServicePRoviderList = new ArrayList<Tab2ChangePojo>();

                    JSONObject jsonObject = new JSONObject(jsonData.toString());
                    JSONArray jsonArray = jsonObject.optJSONArray("message");
                    prgmNameList=new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Tab2ChangePojo searchServiceProviderPojo=new Tab2ChangePojo();
                        JSONObject providerInfoObject=jsonArray.getJSONObject(i);
                        searchServiceProviderPojo.setEmail(providerInfoObject.getString("email"));
                        searchServiceProviderPojo.setName(providerInfoObject.getString("name"));
                        searchServiceProviderPojo.setMobile(providerInfoObject.getString("mobile"));
                        searchServiceProviderPojo.setLati(providerInfoObject.getString("lati"));
                        searchServiceProviderPojo.setLongi(providerInfoObject.getString("longi"));
                        searchServiceProviderPojo.setService_name(providerInfoObject.getString("service_name"));
                        searchServiceProviderPojo.setDate_added(providerInfoObject.getString("date_added"));
                        searchServicePRoviderList.add(searchServiceProviderPojo);
                        prgmNameList[i]=providerInfoObject.getString("name");
                    }
//                    adapter1 = new CustomRecyleViewAdapter(CustomeTextView.this, searchServicePRoviderList,String.valueOf(latitude),String.valueOf(longitude));
//
//                    recyclerView.setAdapter(adapter1);

                    CustomerAdapterTab2 adapter = new CustomerAdapterTab2(context, prgmNameList, address,midammount,searchServicePRoviderList);
                    ListView listView = (ListView) rootView.findViewById(R.id.mobile_list1);
//        ListView listView1=(ListView)findViewById(R.id.image);

                    listView.setAdapter(adapter);




                } catch (Exception e) {

                }
              //  Toast.makeText(getActivity().getApplicationContext(), "" + response, Toast.LENGTH_LONG).show();
                Log.d("response--", response);

            }

            @Override
            protected String doInBackground(Void... params) {
                String request = "http://www.house1.in/app/api/v1/index.php/acceptedRequest";

//                String request = "http://sabkuchhai.in/home/api/v1/index.php/acceptedRequest";
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

                   SharedPreferences sharedPreferences = context.getSharedPreferences("house1",context.MODE_PRIVATE);
                    String tempUserId=sharedPreferences.getString("user_id","");


                    jsonParam.put("service_user_id", tempUserId);
                    Log.d("servi user id n",tempUserId);


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




        return rootView;
    }


}
