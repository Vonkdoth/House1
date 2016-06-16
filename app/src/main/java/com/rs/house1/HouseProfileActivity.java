package com.rs.house1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.rs.adapter.CategoryAdapter;
import com.rs.adapter.SubCategoryAdapter;
import com.rs.helper.ServiceHandler;
import com.rs.helper.StatusCheckerUtility;
import com.rs.model.Category;
import com.rs.model.SubCategory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseProfileActivity extends AppCompatActivity {

    ArrayList<Category> categoryArrayList;
    ArrayList<SubCategory> subCategoryArrayList;
    Category category;
    SubCategory subCategory;
    Spinner spinnerCategory;
    Spinner spinnerSubCategory;
    Button btnNext;
    AutoCompleteTextView txtGPS;
    ImageView img, btnGPS;
    String currentCatString = "";
    String documentPath;
    HttpEntity resEntity;
    String uploadFilePath = null;
    String uploadFileName = null;
    EditText edtDesc;
    int checkCreate = 0;
    ArrayList<Category> arr;
    ArrayList<SubCategory> currentSubCategory;
    ProgressDialog progressDialog;
    double latitude,longitude;
     String sub_Category;
    SharedPreferences sharedPreferences;

    private ArrayAdapter arrayAdapter;
    ArrayList<String> arrayListlocation;
    ArrayList<String> latiArraylist;
    ArrayList<String> longiArraylist;
    ListView listViewlocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_profile);
        //progressDialog = new ProgressDialog(this);
        txtGPS = (AutoCompleteTextView) findViewById(R.id.txtGPS);
        listViewlocation=(ListView)findViewById(R.id.loistlocation);
        listViewlocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getApplicationContext(),"Hiiiiiiii"+position,Toast.LENGTH_SHORT).show();
                txtGPS.setText(arrayListlocation.get(position));
                sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("address",arrayListlocation.get(position));
                editor.putString("lat",latiArraylist.get(position));
                editor.putString("lng",longiArraylist.get(position));
                editor.commit();
                //  onBackPressed();
            }
        });

//        sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
//        String auto=sharedPreferences.getString("address","");
//        txtGPS.setText(auto);

        txtGPS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
           if (txtGPS.getText().toString().length()>2){
                String tempAdd=txtGPS.getText().toString().replace(" ","%20");
                callWebService(tempAdd);
                Log.d("New Addess",tempAdd);
            }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        init();

        spinnerSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (checkCreate != 0) {
                    currentCatString = edtDesc.getText().toString();
                    if (!currentCatString.contains(currentSubCategory.get(position).getSubCategoryName().toString())) {
                        edtDesc.setText(edtDesc.getText().toString() + ", " + currentSubCategory.get(position).getSubCategoryName().toString());
                    } else {
                        edtDesc.setText(currentCatString);
                       // Toast.makeText(HouseProfileActivity.this, currentSubCategory.get(position).getSubCategoryName().toString() + " already exist", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    checkCreate = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(HouseProfileActivity.this, "nothing selected" + " already exist", Toast.LENGTH_SHORT).show();

            }


        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 454);
            }
        });
        //http://www.house1.in/app/
        callWebService1("http://www.house1.in/app/api/v1/index.php/GetCategory");

      //  callWebService1("http://sabkuchhai.in/home/api/v1/index.php/GetCategory");

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category cat = (Category) parent.getSelectedItem();
                SubCategoryAdapter ad = new SubCategoryAdapter(HouseProfileActivity.this, R.id.btnOtp, cat.getSubCategoryArrayList());
               // Log.e("SIZE", cat.getSubCategoryArrayList().size() + "");
               // if (currentSubCategory.size()!=0) {
                    currentSubCategory = cat.getSubCategoryArrayList();
                    spinnerSubCategory.setAdapter(ad);
               // }
                currentCatString = "";
                edtDesc.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btnGPS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String address = "";
                GPSService mGPSService = new GPSService(HouseProfileActivity.this);
                mGPSService.getLocation();

                if (mGPSService.isLocationAvailable == false) {


                    Toast.makeText(HouseProfileActivity.this, "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
                    return;

                } else {

                    // Getting location co-ordinates
                    latitude = mGPSService.getLatitude();
                    longitude = mGPSService.getLongitude();
                    // Toast.makeText(oneActivity.this, "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

                    address = mGPSService.getLocationAddress();

                    // tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
                    txtGPS.setText("" + address);
                    sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("address",address);
                    editor.putString("lat",String.valueOf(latitude));
                    editor.putString("lng",String.valueOf(longitude));
                    editor.commit();
                    //address1.setText("" + address);

                }


                mGPSService.closeGPS();


            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address = "";
                GPSService mGPSService = new GPSService(HouseProfileActivity.this);
                mGPSService.getLocation();

                if (mGPSService.isLocationAvailable == false) {


                    Toast.makeText(HouseProfileActivity.this, "Your location is not available, please try again.", Toast.LENGTH_SHORT).show();
                    return;


                } else {

                    // Getting location co-ordinates
                    latitude = mGPSService.getLatitude();
                    longitude = mGPSService.getLongitude();
                    // Toast.makeText(oneActivity.this, "Latitude:" + latitude + " | Longitude: " + longitude, Toast.LENGTH_LONG).show();

                    address = mGPSService.getLocationAddress();

                    // tvLocation.setText("Latitude: " + latitude + " \nLongitude: " + longitude);
                   // txtGPS.setText("" + address);
                    //address1.setText("" + address);

                }

                mGPSService.closeGPS();



                if (documentPath==null){
                    Toast.makeText(HouseProfileActivity.this,"Please Upload Image",Toast.LENGTH_SHORT).show();
                }
                if (edtDesc.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter Description", Toast.LENGTH_LONG).show();
                } else if (documentPath!=null) {
                  //  Toast.makeText(getApplicationContext(), "Please Upload The Image", Toast.LENGTH_LONG).show();
                }else if (spinnerSubCategory.getSelectedItem()!=null) {
                   if(spinnerSubCategory.getSelectedItem().equals(""))
                        Toast.makeText(getApplicationContext(), "Please Select Sub Category", Toast.LENGTH_LONG).show();

                }else if (spinnerSubCategory.getSelectedItem()==null) {

                        Toast.makeText(getApplicationContext(), "Please Select Sub Category2", Toast.LENGTH_LONG).show();

                }

       // progressDialog.setMessage("Loading.....");
            //    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                final int id=getSharedPreferences("House1", MODE_PRIVATE).getInt("id", 0);

                final String category=(categoryArrayList.get(spinnerCategory.getSelectedItemPosition()).getCatName()).toString();
                sharedPreferences=getSharedPreferences("catagory",MODE_PRIVATE);
                SharedPreferences.Editor editor2=sharedPreferences.edit();
                editor2.putString("catagory",category);
//                editor.putString("lat",String.valueOf(latitude));
//                editor.putString("lng",String.valueOf(longitude));
                editor2.commit();
               if(spinnerSubCategory.getSelectedItem()!=null) {
                   sub_Category = (spinnerSubCategory.getSelectedItem().toString());
               }

                //final int id=getSharedPreferences("House1", MODE_PRIVATE).getInt("id", 0);

                final String subcatagory=(categoryArrayList.get(spinnerCategory.getSelectedItemPosition()).getCatName()).toString();

                final String discription= (edtDesc.getText().toString()).toString();
                sharedPreferences=getSharedPreferences("discriptionprf",MODE_PRIVATE);
                SharedPreferences.Editor editor3=sharedPreferences.edit();
                editor3.putString("discription",discription);
                editor3.commit();
                if (spinnerSubCategory.getSelectedItem()!=null&&!edtDesc.getText().toString().trim().isEmpty()&&documentPath!=null) {

                    try {

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                  //  progressDialog.show();

                    Map<String,String> params=new HashMap<String, String>();
                    File file1 = new File(documentPath);
                    FileBody bin1 = new FileBody(file1);


                    sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                    String newaddress=sharedPreferences.getString("address","");
                    String newlat=sharedPreferences.getString("lat","");

                    String newlong=sharedPreferences.getString("lng","");



//                    changeLocationEditText.setText(auto);
                  //  http://www.house1.in/app/
                    sendMultipartData("http://www.house1.in/app/api/v1/index.php/UpdateProfileService",documentPath,String.valueOf(id),String.valueOf(newlat),String.valueOf(newlong),category,discription);

//    sendMultipartData("http://sabkuchhai.in/home/api/v1/index.php/UpdateProfileService",documentPath,String.valueOf(id),String.valueOf(newlat),String.valueOf(newlong),category,discription);
//
//
//                    new AQuery(HouseProfileActivity.this).ajax(
//                            "http://sabkuchhai.in/home/api/v1/index.php/UpdateProfileService?user_id="+id+"&lati="+String.valueOf(latitude)+"&longi="+String.valueOf(longitude)+"&category="+category+"& description="+discription+"&image="+bin1+"", params, JSONObject.class, new AjaxCallback<JSONObject>() {
//
//                                public void callback(String url, JSONObject jsonObject, AjaxStatus status) {
//
//                                    try {
//
//                                        Log.d("result is",jsonObject.toString());
//
//                                    } catch (Exception e1) {
//
//                                        e1.printStackTrace();
//                                    }
//
//                                    try {
//
//
//                                    } catch (Exception e) {
//
//                                        e.printStackTrace();
//
//                                    }
//
//
//                                    sharedPreferences = getSharedPreferences("house1", MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                                    editor.putString("user_id", String.valueOf(id));
//                                    editor.putString("lati", String.valueOf(latitude));
//                                    editor.putString("longi", String.valueOf(longitude));
//                                    editor.putString("category", String.valueOf(category));
//                                    editor.putString("sub_category", String.valueOf(sub_Category));
//                                    editor.putString("description", String.valueOf(discription));
//                                    editor.putString("image", String.valueOf(documentPath));
//                                    editor.putString("address",txtGPS.getText().toString());
//
//
//                                    editor.commit();
//                                    Intent intent = new Intent(HouseProfileActivity.this, MainActivity.class);
//                                    startActivityForResult(intent, 888);
//                                    progressDialog.dismiss();
//
//                                }
//                            }.method(AQuery.METHOD_POST));


         //  finish();
                }
            }


        });}
    public boolean checkValidation() {
        boolean ret = true;
        if (uploadFilePath == null) {
            ret = false;
        }

        return ret;
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
                uploadFileName = documentPath.substring(index,documentPath.length());
                cursor.close();
                Uri uri = Uri.fromFile(new File(documentPath));
                img.setImageURI(uri);
                img.setImageBitmap(getRoundedCircleBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri), 60));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

            public void init() {
                spinnerCategory = (Spinner) findViewById(R.id.spinCat);
                spinnerSubCategory = (Spinner) findViewById(R.id.spinSubCat);
                btnGPS = (ImageView) findViewById(R.id.gps);
                btnNext = (Button) findViewById(R.id.btnNext);
                txtGPS = (AutoCompleteTextView) findViewById(R.id.txtGPS);
                img = (ImageView) findViewById(R.id.imgProfile);
                edtDesc = (EditText) findViewById(R.id.edtDesc);



            }

            public void callWebService1(final String URL) {
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {
                        String resp = "";
                        try {
                            ServiceHandler handler = new ServiceHandler(HouseProfileActivity.this);
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
                            CategoryAdapter adapter = new CategoryAdapter(HouseProfileActivity.this, R.layout.custom_textview_house_profile, arr);
                            spinnerCategory.setAdapter(adapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();

            }

            public ArrayList<Category> getCategoryData(String resp) {
                try {
                    JSONArray jsonArray = new JSONObject(resp).getJSONArray("data");
                    categoryArrayList = new ArrayList<Category>();
                    if (jsonArray.length() == 0) {
                      //  spinnerSubCategory.setEnabled(false);
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Category category = new Category();
                            category.setCatId(obj.getString("category_id"));
                            category.setCatName(obj.getString("category_name"));
                            subCategoryArrayList = new ArrayList<SubCategory>();
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

                                        subCategoryArrayList.add(subCategory);
                                    }
                                    Log.e("SIZE", subCategoryArrayList.size() + "");
                                    category.setSubCategoryArrayList(subCategoryArrayList);
                                }

                            }


                            categoryArrayList.add(category);
                        }
                    }
                    return categoryArrayList;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

public void sendMultipartData(final String requestURL,final String file_path, final String user_id,final String lati,final String  longi,final String category,final String description ) {
   final String charset = "UTF-8";

    new AsyncTask<Void, Void, List<String>>(){

        @Override
        protected void onPostExecute(List<String> aVoid) {

            super.onPostExecute(aVoid);

            Intent intent = new Intent(HouseProfileActivity.this, MainActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivityForResult(intent, 888);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(HouseProfileActivity.this);
            progressDialog.setMessage("Uploading Please Wait....");
            progressDialog.setCancelable(false);
           progressDialog.show();

        }

        @Override
        protected List<String> doInBackground(Void... params) {
            try {
                MultipartUtility multipart = new MultipartUtility(requestURL, charset);
                multipart.addFormField("user_id", user_id);
                multipart.addFormField("lati", lati);
                multipart.addFormField("longi", longi);
                multipart.addFormField("category", category);
                multipart.addFormField("description", description);
                multipart.addFilePart("image", new File(file_path));
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
                sharedPreferences=getSharedPreferences("location",MODE_PRIVATE);
                SharedPreferences.Editor editor2=sharedPreferences.edit();
               // editor2.putString("address",address);
                editor2.putString("lat",String.valueOf(latitude));
                editor2.putString("lng",String.valueOf(longitude));
                editor2.commit();

                HouseProfileActivity.this.getSharedPreferences("mainloginpref", MODE_PRIVATE).edit().putString("login", "true").commit();
                return response;


            }catch(Exception e){

            }
         return null;
        }
    }.execute();






}

    public void callWebService(final String address) {
        new AQuery(HouseProfileActivity.this).ajax("http://maps.google.com/maps/api/geocode/json?address=" +address+"&ka&sensor=false", new HashMap<String,String>(), JSONObject.class, new AjaxCallback<JSONObject>() {

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
                            arrayAdapter = new ArrayAdapter(HouseProfileActivity.this, android.R.layout.simple_list_item_1, arrayListlocation);

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

    }
