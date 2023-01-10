package com.ayaabdelaziz.azshopping.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.utils.SharedPref;

public class SplashActivity extends AppCompatActivity {

    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        sharedPref = new SharedPref(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent intent = new Intent(SplashActivity.this, SecondScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        }, 3000);


    }
}