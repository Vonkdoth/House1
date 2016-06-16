package com.rs.house1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.rs.adapter.CategoryAdapter;
import com.rs.helper.ServiceHandler;
import com.rs.model.Category;
import com.rs.model.SubCategory;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class AccauntMy extends AppCompatActivity {

    ImageView add_service,imgProfileMyAccount, back, changeLocationPencil, changeMobileNumberPencil, chooseSubServicesPencil, changeLocationImageView;
    TextView validity;
    EditText changeMobileNumberEditText, chooseSubServicesEditText;
    Spinner spinnerSubServices;
    int checkCreate=0;
    String documentPath;
    private ArrayAdapter arrayAdapter;
    ArrayList<String> arrayListlocation;
    Button save;
    AutoCompleteTextView changeLocationEditText;
    SharedPreferences sharedPreferences;
    ArrayList<String> latiArraylist;
    ArrayList<String> longiArraylist;
    ListView listViewlocation;
    String uploadFilePath = null;
    String uploadFileName = null;
    ArrayList<String> arrayList;
    ArrayAdapter<String> subServicesAdapter;
    String currentCatString="";
    ProgressDialog progressDialog;
    double latitude,longitude;
     String response = "x";
    public AQuery androidAQuery;
    AQuery imgaq;//copy
    View view;
    ArrayList<Category> arr;
    Spinner spinnerCategory;

    ArrayList<Category> categoryArrayList;
    ArrayList<SubCategory> subCategoryArrayList;
    Spinner spinnerSubCategory;
    ArrayList<String> subCategoryArrayListString;
    String address1,latnew,longinew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accaunt_my);
        progressDialog=new ProgressDialog(this);
        back = (ImageView) findViewById(R.id.back);
        save=(Button)findViewById(R.id.save);
        listViewlocation=(ListView)findViewById(R.id.loistlocation);


        changeLocationPencil = (ImageView) findViewById(R.id.changeLocationPencil);
        chooseSubServicesPencil = (ImageView) findViewById(R.id.chooseSubServicesPencil);
        changeLocationImageView = (ImageView) findViewById(R.id.changeLocationImageView);
        changeLocationEditText = (AutoCompleteTextView) findViewById(R.id.changeLocationEditText);
//        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
//        String auto=sharedPreferences.getString("address","");
//        changeLocationEditText.setText(auto);

        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
        //editor.putString("address",address);
        address1=sharedPreferences.getString("address","");
        changeLocationEditText.setText(address1);


        changeMobileNumberEditText = (EditText) findViewById(R.id.changeMobileNumberEditText);
        chooseSubServicesEditText = (EditText) findViewById(R.id.chooseSubServicesEditText);
        imgProfileMyAccount=(ImageView)findViewById(R.id.imgProfileMyAccount);
        spinnerSubServices = (Spinner) findViewById(R.id.spinnerSubServices);
        arrayList=new ArrayList<String>();
        imgaq=new AQuery(getApplicationContext());

        subServicesAdapter = new ArrayAdapter<String>(AccauntMy.this, R.layout.custom_textview_house_profile, arrayList);

        spinnerSubServices.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (checkCreate != 0) {
                    currentCatString = chooseSubServicesEditText.getText().toString();

                    if (!currentCatString.contains(arrayList.get(position).toString())) {
                        chooseSubServicesEditText.setText(chooseSubServicesEditText.getText().toString() + ", " + arrayList.get(position).toString());

                        final String discription1= (chooseSubServicesEditText.getText().toString()).toString();
                        sharedPreferences=getSharedPreferences("discriptionprf",MODE_PRIVATE);
                        SharedPreferences.Editor editor3=sharedPreferences.edit();
                        editor3.putString("discription",discription1);
                        editor3.commit();

                    } else {
                        chooseSubServicesEditText.setText(currentCatString);
                    }
                } else {
                    checkCreate = 1;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        new AsyncTask<Void, Void, String>() {

            final int id=getApplicationContext().getSharedPreferences("House1", Context. MODE_PRIVATE).getInt("id", 0);


            @Override
            protected void onPostExecute(String jsonData) {
                super.onPostExecute(jsonData);
                try {
                    ArrayList<String> searchServicePRoviderList = new ArrayList<String>();

                    JSONObject jsonObject = new JSONObject(jsonData.toString());
                    JSONObject jsonObject1 = jsonObject.optJSONObject("message");


                    try {
                        imgaq.id(imgProfileMyAccount).image(jsonObject1.getString("profile_image"), true, true, 240, 0, null, AQuery.FADE_IN_NETWORK, 0.0f);
                        Log.d("Image is", jsonObject1.getString("profile_image"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Log.d("mobileno is",jsonObject1.getString("contact_no"));

                } catch (Exception e) {

                }
                Log.d("response--", response);
            }
            @Override
            protected String doInBackground(Void... params) {
               // http://www.house1.in/app/
                String request = "http://www.house1.in/app/api/v1/index.php/serviceProfile";

//                String request = "http://sabkuchhai.in/home/api/v1/index.php/serviceProfile";
                try {

                    URL url = new URL(request);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");
                    connection.setUseCaches(false);
                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("service_user_id", id);
                    Log.d("Service User Id",String.valueOf(id));
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        imgProfileMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 454);
            }
        });
        changeLocationPencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLocationImageView.setImageResource(R.drawable.location);
                chooseSubServicesEditText.setEnabled(false);
                changeMobileNumberEditText.setEnabled(false);
                changeLocationEditText.setEnabled(true);
                changeLocationEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(changeLocationEditText, InputMethodManager.SHOW_IMPLICIT);
                subServicesAdapter.clear();
            }
        });

        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
        //editor.putString("address",address);
        address1=sharedPreferences.getString("address","");
        changeLocationEditText.setText(address1);
//
//        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
//        String address=sharedPreferences.getString("address","");
//        changeLocationEditText.setText(address);


        sharedPreferences=getSharedPreferences("catagory",MODE_PRIVATE);
        String catagory=sharedPreferences.getString("catagory","");
        changeMobileNumberEditText.setText(catagory);

        sharedPreferences=getSharedPreferences("discriptionprf",MODE_PRIVATE);
        String subcatagory=sharedPreferences.getString("discription","");
        chooseSubServicesEditText.setText(subcatagory);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                //editor.putString("address",address);
                address1=sharedPreferences.getString("address","");
                changeLocationEditText.setText(address1);


                final String discription1= (chooseSubServicesEditText.getText().toString()).toString();
                sharedPreferences=getSharedPreferences("discriptionprf",MODE_PRIVATE);
                SharedPreferences.Editor editor3=sharedPreferences.edit();
                editor3.putString("discription",discription1);
                editor3.commit();


//                sharedPreferences=getSharedPreferences("discriptionprf",MODE_PRIVATE);
//                String subcatagory=sharedPreferences.getString("discription","");
//                chooseSubServicesEditText.setText(subcatagory);

                progressDialog.setMessage("Loading.....");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                final String nid=getSharedPreferences("house1", MODE_PRIVATE).getString("id", "");
                final String ncategory=getSharedPreferences("house1", MODE_PRIVATE).getString("category", "");
                final String nsub_Category=getSharedPreferences("house1", MODE_PRIVATE).getString("sub_category", "");
                final String ndiscription=getSharedPreferences("house1", MODE_PRIVATE).getString("description", "");
                final String nlat=getSharedPreferences("house1", MODE_PRIVATE).getString("lati", "");
                final String nlongi=getSharedPreferences("house1", MODE_PRIVATE).getString("longi", "");
                final String nimage=getSharedPreferences("house1", MODE_PRIVATE).getString("image", "");
                final int id=getSharedPreferences("House1", MODE_PRIVATE).getInt("id", 0);





                if (!changeMobileNumberEditText.getText().toString().trim().isEmpty()) {


                    sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                     latnew=sharedPreferences.getString("lat","");
                  longinew=sharedPreferences.getString("lng","");
//                    Log.d("latnew",latnew);
//                    Log.d("longinew",longinew);

//                               http://www.house1.in/app/                                                                    documentPath,String.valueOf(id),String.valueOf(newlat),String.valueOf(newlong),category,discription
//                    sendMultipartData("http://sabkuchhai.in/home/api/v1/index.php/UpdateProfileService",documentPath,String.valueOf(id),String.valueOf(latnew),String.valueOf(longinew),ncategory,chooseSubServicesEditText.getText().toString());

                    sendMultipartData("http://www.house1.in/app/api/v1/index.php/UpdateProfileService",documentPath,String.valueOf(id),String.valueOf(latnew),String.valueOf(longinew),ncategory,chooseSubServicesEditText.getText().toString());

                }
            }
        });


        chooseSubServicesPencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLocationImageView.setImageBitmap(null);
                chooseSubServicesEditText.setEnabled(true);
                changeMobileNumberEditText.setEnabled(false);
                changeLocationEditText.setEnabled(false);
                chooseSubServicesEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(chooseSubServicesEditText, InputMethodManager.SHOW_IMPLICIT);
               // http://www.house1.in/app/
                callWebService2("http://www.house1.in/app/api/v1/index.php/GetCategory");

//                callWebService2("http://sabkuchhai.in/home/api/v1/index.php/GetCategory");


            }
        });

        listViewlocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeLocationEditText.setText(arrayListlocation.get(position));
                sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("address",arrayListlocation.get(position));
                editor.putString("lat",latiArraylist.get(position));
                editor.putString("lng",longiArraylist.get(position));
                editor.commit();
                Log.d("latlistview",latiArraylist.get(position));
                Log.d("longilistview",longiArraylist.get(position));

            }
        });
        changeLocationImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String address = "";
                GPSService mGPSService = new GPSService(AccauntMy.this);
                mGPSService.getLocation();

                if (mGPSService.isLocationAvailable == false) {

                    Toast.makeText(AccauntMy.this, "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
                    return;


                } else {
                    double latitude = mGPSService.getLatitude();
                    double longitude = mGPSService.getLongitude();

                    address = mGPSService.getLocationAddress();

                    changeLocationEditText.setText("" + address);

                    sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("address",address);
                    editor.putString("lat",String.valueOf(latitude));
                    editor.putString("lng",String.valueOf(longitude));
                    Log.d("currentlati",String.valueOf(latitude));
                    Log.d("currentlogi",String.valueOf(longitude));



                    editor.commit();
                }

                mGPSService.closeGPS();


            }
        });
        changeLocationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (changeLocationEditText.getText().toString().length()>2){
                    String tempAdd=changeLocationEditText.getText().toString().replace(" ","%20");
                    callWebService(tempAdd);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void callWebService(final String address) {

        new AQuery(AccauntMy.this).ajax("http://maps.google.com/maps/api/geocode/json?address=" +address+"&ka&sensor=false"
                , new HashMap<String,String>(), JSONObject.class, new AjaxCallback<JSONObject>() {

                    public void callback(String url, JSONObject jsonObject, AjaxStatus status) {

                        try {

                            Log.d("result is",jsonObject.toString());
                            JSONObject getLocationObj = new JSONObject(jsonObject.toString());
                            JSONArray GetLocations = getLocationObj.getJSONArray("results");
                            arrayListlocation=new ArrayList<String>();
                            latiArraylist=new ArrayList<String>();
                            longiArraylist=new ArrayList<String>();
                            for (int i = 0; i < GetLocations.length(); i++) {
                                JSONObject objLocations = GetLocations.getJSONObject(i);
                                String  location=objLocations.getString("formatted_address");
                                arrayListlocation.add(location);

                                JSONObject geometrylocation=objLocations.getJSONObject("geometry").getJSONObject("location");

                                latiArraylist.add(geometrylocation.getString("lat"));
                                longiArraylist.add(geometrylocation.getString("lng"));

//

                            }
                            arrayAdapter = new ArrayAdapter(AccauntMy.this, android.R.layout.simple_list_item_1, arrayListlocation);

                            listViewlocation.setAdapter(arrayAdapter);
                        } catch (Exception e1) {

                            e1.printStackTrace();
                        }

                        try {
//
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.method(AQuery.METHOD_POST));





    }
    public void callWebService1(final String URL) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String resp = "";
                try {
                    ServiceHandler handler = new ServiceHandler(AccauntMy.this);
                    resp = handler.sendPost(URL, "");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return resp;
            }

            @Override
            protected void onPostExecute(String s) {
                try {
                   arr = getCategoryData(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();

    }

    public ArrayList<Category> getCategoryData(String resp) {
        try {
            Log.d("getcatagory data----",resp);
            JSONArray jsonArray = new JSONObject(resp).getJSONArray("data");
            categoryArrayList = new ArrayList<Category>();
            if (jsonArray.length() == 0) {
               // spinnerSubCategory.setEnabled(false);
            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    Category category = new Category();
                    category.setCatId(obj.getString("category_id"));
                    category.setCatName(obj.getString("category_name"));
                    subCategoryArrayList = new ArrayList<SubCategory>();
                    subCategoryArrayListString=new ArrayList<String>();
                    JSONArray subCatArray = obj.getJSONArray("sub_category");
                    {
                        if (subCatArray.length() == 0) {
                           // spinnerSubCategory.setEnabled(false);
                        } else {
                            for (int j = 0; j < subCatArray.length(); j++) {
                                JSONObject object = subCatArray.getJSONObject(j);

                                SubCategory subCategory = new SubCategory();
                                subCategory.setCategoryId(object.getString("sab_category_id"));
                                subCategory.setSubCategoryName(object.getString("sab_category_name"));
                                subCategory.setCategoryId(object.getString("parent_id"));
                                subCategoryArrayListString.add(object.getString("sab_category_name"));
                                subCategoryArrayList.add(subCategory);
                            }
                            Log.e("SIZE", subCategoryArrayList.size() + "");
                        }

                    }

                    category.setSubCategoryArrayList(subCategoryArrayList);
                    categoryArrayList.add(category);
                }
            }
            return categoryArrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 454) {
            try {

                int index = 0;
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(
                        selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                documentPath = cursor.getString(columnIndex);
                index = documentPath.lastIndexOf("/");
                uploadFileName = documentPath.substring(index,
                        documentPath.length());
                cursor.close();
                Uri uri = Uri.fromFile(new File(documentPath));
                imgProfileMyAccount.setImageURI(uri);
                //imgProfileMyAccount.setImageBitmap(nimage);
                imgProfileMyAccount.setImageBitmap(getRoundedCircleBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri), 60));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Bitmap getRoundedCircleBitmap(Bitmap bitmap, int pixels) {

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
    public void sendMultipartData(final String requestURL,final String file_path, final String user_id,final String lati,final String  longi,final String category,final String description ) {
        final String charset = "UTF-8";
        new AsyncTask<Void, Void, List<String>>() {
            @Override
            protected void onPostExecute(List<String> aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(AccauntMy.this, MainActivity.class);
                startActivityForResult(intent, 888);
                sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);

                address1=sharedPreferences.getString("address","");
                changeLocationEditText.setText(address1);



                Log.d("latnew",latnew);
                Log.d("longinew",longinew);
            }

            @Override
            protected List<String> doInBackground(Void... params) {
                try {
//                    URL url = new URL("");
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                    connection.setDoOutput(true);
//                    connection.setDoInput(true);
//                    //  connection.setInstanceFollowRedirects(false);
//                    connection.setRequestMethod("POST");
//                    connection.setRequestProperty("Content-Type", "application/json");
//                    connection.setRequestProperty("charset", "utf-8");
//                    //connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
//                    connection.setUseCaches(false);
//                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//
//                    JSONObject jsonParam = new JSONObject();
//                    //latnew,longinew;
//                    jsonParam.put("user_id", user_id);
//                    jsonParam.put("lati", latnew);
//                    jsonParam.put("longi", longinew);//22.726026
//                    jsonParam.put("category", category);//75.825432
//                    jsonParam.put("description", description);//75.825432
//                    jsonParam.put("image", new File(file_path));//75.825432
//                    jsonParam.put("longi", longinew);//75.825432
//                    jsonParam.put("longi", longinew);//75.825432


                    sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                    String latitutenew=sharedPreferences.getString("lat","");
                    String longitudenew=sharedPreferences.getString("lng","");
                    Log.d("latitutenew.....",latitutenew);
                    Log.d("longitudenew...",longitudenew);
                   // lati,final String  longi,

                    MultipartUtility multipart = new MultipartUtility(requestURL, charset);
                    multipart.addFormField("user_id", user_id);
                    multipart.addFormField("lati", latitutenew);
                    multipart.addFormField("longi", longitudenew);
                    multipart.addFormField("category", category);
                    multipart.addFormField("description", description);
                   try {
                       multipart.addFilePart("image", new File(file_path));
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                    List<String> response = multipart.finish();

                    sharedPreferences = getSharedPreferences("house1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_id", user_id);
                  editor.putString("lati", lati);
                  editor.putString("longi", longi);
                    editor.putString("category", category);
                    editor.putString("sub_category", "");
                    editor.putString("description", description);
                    editor.putString("image", file_path);


                    editor.commit();

                    AccauntMy.this.getSharedPreferences("mainloginpref", MODE_PRIVATE).edit().putString("login", "true").commit();

                    return response;


                } catch (Exception e) {
                    e.printStackTrace();
                }
//                } Log.d("latnewj",latnew);
//                Log.d("longinewj",longinew);
                return null;
            }
        }.execute();
    }
        public void callWebService2(final String URL) {
            new AsyncTask<Void, Void, String>() {

                @Override
                protected String doInBackground(Void... params) {
                    String resp = "";
                    try {
                        ServiceHandler handler = new ServiceHandler(AccauntMy.this);
                        resp = handler.sendPost(URL, "");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return resp;
                }

                @Override
                protected void onPostExecute(String s) {
                    try {
                        arr = getCategoryData2(s);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();

        }

        public ArrayList<Category> getCategoryData2(String resp) {
            try {
                arrayList = new ArrayList<String>();
                JSONArray jsonArray = new JSONObject(resp).getJSONArray("data");
                categoryArrayList = new ArrayList<Category>();
                if (jsonArray.length() == 0) {
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Category category = new Category();
                        category.setCatId(obj.getString("category_id"));
                        category.setCatName(obj.getString("category_name"));

                        sharedPreferences=getSharedPreferences("catagory",MODE_PRIVATE);
                        String catagory=sharedPreferences.getString("catagory","");
                        changeMobileNumberEditText.setText(catagory);
//                        if (obj.getString("category_name").equals(catagory)){
//
//                        }
                        subCategoryArrayList = new ArrayList<SubCategory>();
                        JSONArray subCatArray = obj.getJSONArray("sub_category");
                        {
                            if (subCatArray.length() == 0) {
                               // spinnerSubCategory.setEnabled(false);
                            } else {
                                for (int j = 0; j < subCatArray.length(); j++) {

                                    JSONObject object = subCatArray.getJSONObject(j);
                                    if (obj.getString("category_name").equals(catagory)){

                                        arrayList.add(object.getString("sab_category_name"));
                                    }
                                    SubCategory subCategory = new SubCategory();
                                    subCategory.setCategoryId(object.getString("sab_category_id"));
                                    subCategory.setSubCategoryName(object.getString("sab_category_name"));
                                    subCategory.setCategoryId(object.getString("parent_id"));

                                    subCategoryArrayList.add(subCategory);
                                }
                                Log.e("SIZE", subCategoryArrayList.size() + "");
                                Log.d("Subcategory is",String.valueOf(subCategoryArrayList));
                                //Toast.makeText(getApplicationContext(),"Subcategory"+subCategoryArrayList,Toast.LENGTH_LONG).show();
                                subServicesAdapter = new ArrayAdapter<String>(AccauntMy.this, R.layout.custom_textview_house_profile, arrayList);
                                spinnerSubServices.setAdapter(subServicesAdapter);
                            }

                        }

                        category.setSubCategoryArrayList(subCategoryArrayList);
                        categoryArrayList.add(category);

                    }
                }
                return categoryArrayList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


    }



