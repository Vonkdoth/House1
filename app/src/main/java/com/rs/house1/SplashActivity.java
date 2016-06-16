package com.rs.house1;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SplashActivity.this.getSharedPreferences("mainloginpref",MODE_PRIVATE).getString("login","false").equals("false")) {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 123);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivityForResult(intent, 123);
                    finish();
                }
            }
        },5000);

    }
}
