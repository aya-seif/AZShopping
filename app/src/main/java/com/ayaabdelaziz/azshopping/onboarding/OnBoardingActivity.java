package com.ayaabdelaziz.azshopping.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ayaabdelaziz.azshopping.auth.ui.MainActivity;
import com.ayaabdelaziz.azshopping.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    LinearLayout linearLayout;
    DotsIndicator dotsIndicator;
    FloatingActionButton switch_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        dotsIndicator = findViewById(R.id.dots_indicator);
        switch_btn=findViewById(R.id.switch_btn);

        viewPager = findViewById(R.id.on_boardingVB);
        viewPagerAdapter = new ViewPagerAdapter(this);
        linearLayout = findViewById(R.id.indicator);
        viewPager.setAdapter(viewPagerAdapter);
        dotsIndicator.attachTo(viewPager);

        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }


}