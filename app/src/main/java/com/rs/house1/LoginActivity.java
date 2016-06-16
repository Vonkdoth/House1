package com.rs.house1;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rs.helper.StatusCheckerUtility;
import com.rs.helper.Validation;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class LoginActivity extends Activity {
    Button btnSubmit, btnOtp;
    EditText edtName;
    EditText edtNumber, edtOTP;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnOtp = (Button) findViewById(R.id.btnOtp);
        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);
        edtOTP = (EditText) findViewById(R.id.edtOtp);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StatusCheckerUtility.isConnected(LoginActivity.this)) {
                    if (checkValidation()) {
                        sharedPreferences=getSharedPreferences("logindata",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
//                        edtName.getText().toString());
//                        jsonObject.accumulate("mobile", edtNumber.getText().toString());
                        editor.putString("Name", edtName.getText().toString());
                        editor.putString("Mobile no.",edtNumber.getText().toString());
                        editor.commit();
                        new AsyncTask<Void, Void, String>() {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                progressDialog = new ProgressDialog(LoginActivity.this);
                                progressDialog.setMessage("Please Wait....");
                                progressDialog.setCancelable(false);
                                progressDialog.show();

                            }

                            @Override
                            protected String doInBackground(Void... params) {
                                String resp = "";
                                try {
                                    resp = doSendId("http://www.house1.in/app/api/v1/index.php/SignUp");

//                                    resp = doSendId("http://sabkuchhai.in/home/api/v1/index.php/SignUp");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return resp;
                            }

                            @Override
                            protected void onPostExecute(String aVoid) {
                                try {
                                    if (new JSONObject(aVoid).getBoolean("status") == true) {
                                        //if(Validation.hasText(edtOTP)&&edtOTP.getText().length()>=4) {
                                        edtName.setVisibility(EditText.GONE);
                                        edtOTP.setVisibility(EditText.VISIBLE);
                                        edtNumber.setEnabled(false);
                                        btnSubmit.setVisibility(Button.GONE);
                                        btnOtp.setVisibility(Button.VISIBLE);
                                        progressDialog.dismiss();

                                        //}
                                    } else {
                                        Toast.makeText(LoginActivity.this, new JSONObject(aVoid).getString("message"), Toast.LENGTH_LONG).show();

                                    }
                                    /*{"status":"true","message":"successfully Sign In","data":1234}*/

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.execute();
                    }
                }


            }
        });


        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog = new ProgressDialog(LoginActivity.this);
                        progressDialog.setMessage("Please Wait....");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                    }

                    @Override
                    protected String doInBackground(Void... params) {
                        String resp = "";
                        try {
//                            http://www.house1.in/app/
                            resp = doSendId1("http://www.house1.in/app/api/v1/index.php/MobileVerify");

//                            resp = doSendId1("http://sabkuchhai.in/home/api/v1/index.php/MobileVerify");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return resp;
                    }

                    @Override
                    protected void onPostExecute(String aVoid) {
                        try {
                            if (new JSONObject(aVoid).getBoolean("status") == true) {
                                /* {"status":"true","message":"success",
                                "data":{"id":9,"category":"","name":"cvhj",
                                "email":"","mobile":6696666666,"password":"",
                                "city":"","profile_image":"","cover_image":"",
                                "experience":"","qualification":"","number_client":"",
                                "introducation":"","aditional_info":"","lati":"","longi":"",
                                "device_token":"","device_type":"","verify_code":"",
                                "insert_at":"2016-04-11","update_at":"2016-04-11 11:22:46",
                                "status":1}}*/

                                getSharedPreferences("House1", MODE_PRIVATE).edit().putInt("id", new JSONObject(aVoid).getJSONObject("data").getInt("id")).commit();
                                Intent intent = new Intent(LoginActivity.this, HouseProfileActivity.class);
                                startActivityForResult(intent, 345);
                                progressDialog.dismiss();
                                finish();
                            } else {
                               // Toast.makeText(LoginActivity.this, new JSONObject(aVoid).getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


            }
        });

    }

    public boolean checkValidation() {
        boolean ret = true;
        if (!Validation.isFirstName(edtName, true)) {
            ret = false;
        }
        if (!Validation.isPhoneNumber(edtNumber, true)) {
            ret = false;
        }
        return ret;
    }


    public String doSendId(String url) {
        String response_str = null;

        try {
            HttpEntity resEntity;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", edtName.getText().toString());
            jsonObject.accumulate("mobile", edtNumber.getText().toString());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            response_str = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(response_str);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return response_str;
    }

    public String doSendId1(String url) {
        String response_str = null;

        try {
            HttpEntity resEntity;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("mobile", edtNumber.getText().toString());
            jsonObject.accumulate("verify_code", edtOTP.getText().toString());//edtOTP.getText().toString());

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);

            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            response_str = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(response_str);

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return response_str;
    }
}
