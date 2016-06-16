package com.rs.house1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//
//import customersrepository.fixedout.com.paylow.MainTabActivity;
//import customersrepository.fixedout.com.paylow.OTPActivity;

/**
 * Created by Akash on 4/8/2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = SmsReceiver.class.getSimpleName();
    SharedPreferences shPref;
    String SHAREDPREF = "mobile_no_pref";
    Context thisContext;
    SharedPreferences sharedPreferences;
    String verificationCode;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OTP NEW RUNNING","fnhgfyfny");
        shPref = context.getSharedPreferences(SHAREDPREF, Context.MODE_PRIVATE);
        thisContext = context;
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (Object aPdusObj : pdusObj) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) aPdusObj);
                    String senderAddress = currentMessage.getDisplayOriginatingAddress();
                    String message = currentMessage.getDisplayMessageBody();

                    Log.e(TAG, "Received SMS: " + message + ", Sender: " + senderAddress);

                    // if the SMS is not from our gateway, ignore the message
                    if (!(senderAddress.toLowerCase().contains("IM-HOUSEO".toLowerCase()) || senderAddress.toLowerCase().contains("IM-HOUSEO".toLowerCase()))) {
                        return;
                    }

                    // verification code from sms
                    verificationCode = getVerificationCode(message);

                    Log.e(TAG, "OTP received: " + verificationCode);
                    enterMobilePostByBroadcast(verificationCode);
//                    Intent hhtpIntent = new Intent(context, HttpService.class);
//                    hhtpIntent.putExtra("otp", verificationCode);
//                    context.startService(hhtpIntent);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Getting the OTP from sms message body
     * ':' is the separator of OTP from the message
     *
     * @param message
     * @return
     */
    private String getVerificationCode(String message) {
        String code = "";

        if (!message.equals("")) {
            code = message.replaceAll("\\D+", "");
            // code = message.substring(1, 4);
            return code;
        }
//        if (index != -1) {
//            int start = index + 2;
//            int length = 6;
//            code = message.substring(start, start + length);
//            return code;
//        }

        return code;
    }

    public void enterMobilePostByBroadcast(final String codeDataBySms) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String resp = "";
                try {
                    resp = doSendId1("http://www.house1.in/app/api/v1/index.php/MobileVerify");

//                    resp = doSendId1("http://sabkuchhai.in/home/api/v1/index.php/MobileVerify");
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

                        thisContext.getSharedPreferences("House1", thisContext.MODE_PRIVATE).edit().putInt("id", new JSONObject(aVoid).getJSONObject("data").getInt("id")).commit();
                        Intent intent = new Intent(thisContext.getApplicationContext(), HouseProfileActivity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//AG_ACTIVITY_NEW_TASK
                        thisContext.startActivity(intent);

                       // finish();
                    } else {
                        Toast.makeText(thisContext, new JSONObject(aVoid).getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.execute();
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

               sharedPreferences=thisContext.getSharedPreferences("logindata",thisContext.MODE_PRIVATE);
                String mobileno=sharedPreferences.getString("Mobile no.","");
                // editor2.putString("catagory",category);
               // changeMobileNumberEditText.setText(service.getString("catagory",""));

                jsonObject.accumulate("mobile", mobileno);
                jsonObject.accumulate("verify_code", verificationCode);//edtOTP.getText().toString());

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
//String tempMobileNo=shPref.getString("mobile_no", "0");
//        Map<String, String> paramsHeaders = Utility.getHeaders(thisContext);
//
//        Map<String, String> params = new HashMap<>();
//
//
//        params.put("mobile_no", tempMobileNo);
//        params.put("otp", codeDataBySms);


//        new AQuery(thisContext).ajax(Constants2.Otp_url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
//
//            public void callback(String url, JSONObject jsonObject, AjaxStatus status) {
//
//
//                if (jsonObject == null) {
//                    Toast.makeText(thisContext, "Json is null", Toast.LENGTH_SHORT).show();
//                }
//                if (jsonObject.toString().contains("true")) {
//                    Intent intent = new Intent(thisContext, MainTabActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    thisContext.startActivity(intent);
//                    Toast.makeText(thisContext, jsonObject.toString(), Toast.LENGTH_LONG).show();
//                    Log.d("Enter OTP Result is ", jsonObject.toString());
//
//                } else {
//                    Toast.makeText(thisContext, "Incorrect OTP", Toast.LENGTH_LONG).show();
//                    Log.d("Enter OTP Result is ", jsonObject.toString());
//
//                }
//            }
//        }.headers(paramsHeaders));

//    public String doSendId1(String url) {
//        String response_str = null;
//
//        try {
//            HttpEntity resEntity;
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost(url);
//            String json = "";
//
//            // 3. build jsonObject
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.accumulate("mobile", edtNumber.getText().toString());
//            jsonObject.accumulate("verify_code", edtOTP.getText().toString());//edtOTP.getText().toString());
//
//            // 4. convert JSONObject to JSON to String
//            json = jsonObject.toString();
//
//            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
//            // ObjectMapper mapper = new ObjectMapper();
//            // json = mapper.writeValueAsString(person);
//
//            // 5. set json to StringEntity
//            StringEntity se = new StringEntity(json);
//
//            // 6. set httpPost Entity
//            httpPost.setEntity(se);
//
//            // 7. Set some headers to inform server about the type of the content
//            httpPost.setHeader("Accept", "application/json");
//            httpPost.setHeader("Content-type", "application/json");
//
//            // 8. Execute POST request to the given URL
//            HttpResponse httpResponse = httpclient.execute(httpPost);
//
//            response_str = EntityUtils.toString(httpResponse.getEntity());
//            System.out.println(response_str);
//
//        } catch (Exception e) {
//            Log.d("InputStream", e.getLocalizedMessage());
//        }
//
//        // 11. return result
//        return response_str;
    //}

}

