package com.rs.house1;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
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
public class MyAccountFragment extends Fragment {
    private RatingBar ratingBar;
    TextView name,servicename,mobileno,rating,validity;
    ImageView profilepic;
    String response = "x";
    public AQuery androidAQuery;
    AQuery imgaq;//copy
    View view;
    ArrayList<String> imageArrayList;
    ImageView starrating;
ProgressDialog progressBar;
    SharedPreferences sharedPreferences;

    public MyAccountFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myaccaunt_fragment, container, false);

        imgaq=new AQuery(getActivity().getApplicationContext());
                name=(TextView)rootView.findViewById(R.id.name);
        servicename=(TextView)rootView.findViewById(R.id.servicename);
        mobileno=(TextView)rootView.findViewById(R.id.mobileno);
        rating=(TextView)rootView.findViewById(R.id.rate);
      //  validity=(TextView)rootView.findViewById(R.id.validity);
       // ratingBar=(RatingBar)rootView.findViewById(R.id.ratingBar);

        profilepic=(ImageView)rootView.findViewById(R.id.profilepic);
        sharedPreferences=getActivity().getApplicationContext().getSharedPreferences("catagory",Context.MODE_PRIVATE);
       // sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
        String newcatagory=sharedPreferences.getString("catagory","");
       // String newlat=sharedPreferences.getString("lat","");

//        progressBar=new ProgressDialog(getActivity().getApplicationContext());
//        progressBar.setMessage("Loading...");
//        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressBar.setIndeterminate(true);
//        progressBar.show();
       // progressBar.setCancelable(false);



        new AsyncTask<Void, Void, String>() {
            final int id=getActivity().getApplicationContext().getSharedPreferences("House1", Context. MODE_PRIVATE).getInt("id", 0);


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
                    ArrayList<String> searchServicePRoviderList = new ArrayList<String>();

//
//                    {
//                        "status": "true",
//                            "message": {
//                        "id": 127,
//                                "name": "ankit",
//                                "contact_no": 78282807891,
//                                "category_name": 78282807891,
//                                "profile_image": "http://sabkuchhai.in/home/uploads/profile/1463064275041a49fe8914df0eada4d4b7d530d7fa5ba.jpg",
//                                "rating": 5
//                    }
//                    }
                    JSONObject jsonObject = new JSONObject(jsonData.toString());
                    JSONObject jsonObject1 = jsonObject.optJSONObject("message");
                    name.setText(jsonObject1.getString("name"));
                    servicename.setText(jsonObject1.getString("category_name"));
                    mobileno.setText(jsonObject1.getString("contact_no"));
                   // rating.setText(jsonObject1.getString("rating"));
                rating.setText(String.format("%.1f ",Float.valueOf(jsonObject1.getString("rating"))));

//                    profilepic.set
             try {
                 imgaq.id(profilepic).image(jsonObject1.getString("profile_image"), true, true, 240, 0, null, AQuery.FADE_IN_NETWORK, 0.0f);
                // Log.d("Image is", jsonObject1.getString("profile_image"));
             }catch (Exception e){
                 e.printStackTrace();
             }
                  //  Log.d("mobileno is",jsonObject1.getString("contact_no"));


                    // validity.setText(jsonObject1.getString("name"));

                    //  prgmNameList=new String[jsonArray.length()];
                   // for (int i = 0; i < jsonArray.length(); i++) {
//                        Tab2ChangePojo searchServiceProviderPojo=new Tab2ChangePojo();
//                        JSONObject providerInfoObject=jsonArray.getJSONObject(i);
//                        searchServiceProviderPojo.setEmail(providerInfoObject.getString("email"));
//                        searchServiceProviderPojo.setName(providerInfoObject.getString("name"));
//                        searchServiceProviderPojo.setMobile(providerInfoObject.getString("mobile"));
//                        searchServiceProviderPojo.setLati(providerInfoObject.getString("lati"));
//                        searchServiceProviderPojo.setLongi(providerInfoObject.getString("longi"));
//                        searchServiceProviderPojo.setService_name(providerInfoObject.getString("service_name"));
//                        searchServiceProviderPojo.setDate_added(providerInfoObject.getString("date_added"));
//                        searchServicePRoviderList.add(searchServiceProviderPojo);
//                        prgmNameList[i]=providerInfoObject.getString("name");
//                    }
//                    adapter1 = new CustomRecyleViewAdapter(CustomeTextView.this, searchServicePRoviderList,String.valueOf(latitude),String.valueOf(longitude));
//
//                    recyclerView.setAdapter(adapter1);

//                    CustomerAdapterTab2 adapter = new CustomerAdapterTab2(context, prgmNameList, address,midammount,searchServicePRoviderList);
//                    ListView listView = (ListView) rootView.findViewById(R.id.mobile_list1);
////        ListView listView1=(ListView)findViewById(R.id.image);
//
//                    listView.setAdapter(adapter);




                } catch (Exception e) {

                }
               // Toast.makeText(getActivity().getApplicationContext(), "" + response, Toast.LENGTH_LONG).show();
                Log.d("response--", response);
//progressBar.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                //String request = "http://sabkuchhai.in/home/api/v1/index.php/serviceProfile";

                String request = "http://www.house1.in/app/api/v1/index.php/serviceProfile";
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
                            jsonParam.put("service_user_id", id);
//                            jsonParam.put("description", "Real");
//                            jsonParam.put("enable", "true");

//                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("house1",context.MODE_PRIVATE);
//                    String tempUserId=sharedPreferences.getString("user_id","");

//
  //                  jsonParam.put("service_user_id", tempUserId);

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

       // addListenerOnRatingBar(rootView);
        return rootView;
    }
    public Bitmap getRoundedCircleBitmap(Bitmap bitmap, int pixels) {

        int size = Math.min(bitmap.getWidth(), bitmap.getHeight());

        int x = (bitmap.getWidth() - size) / 2;
        int y = (bitmap.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(bitmap, x, y, size, size);
        if (squaredBitmap != bitmap) {
            bitmap.recycle();
        }

        Bitmap bitmap1 = Bitmap.createBitmap(size, size, bitmap.getConfig());

        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        squaredBitmap.recycle();
        return bitmap1;

    }


//    public void addListenerOnRatingBar(View rootView) {
//
//        ratingBar = (RatingBar)rootView.findViewById(R.id.ratingBar);
//        //  txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);
//
//        //if rating value is changed,
//        //display the current rating value in the result (textview) automatically
//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            public void onRatingChanged(RatingBar ratingBar, float rating,
//                                        boolean fromUser) {
//
//               // txtRatingValue.setText(String.valueOf(rating));
//
//            }
//        });
//        ratingBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity().getApplicationContext(),
//                        String.valueOf(ratingBar.getRating()),
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
//}