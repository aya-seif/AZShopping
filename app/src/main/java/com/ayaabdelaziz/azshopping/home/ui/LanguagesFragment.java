package com.ayaabdelaziz.azshopping.home.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayaabdelaziz.azshopping.R;

import java.util.Locale;


public class LanguagesFragment extends Fragment {


    ImageView iv_back_lang;
    TextView arabic, english;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_languages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_back_lang = view.findViewById(R.id.iv_back_lang);
        english = view.findViewById(R.id.english);
        arabic = view.findViewById(R.id.arabic);
        iv_back_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LanguagesFragment.this).navigate(R.id.action_languagesFragment_to_profileFragment);
            }
        });

        arabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLang("ar");
                NavHostFragment.findNavController(LanguagesFragment.this).navigate(R.id.action_languagesFragment_to_profileFragment);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLang("en");
                NavHostFragment.findNavController(LanguagesFragment.this).navigate(R.id.action_languagesFragment_to_profileFragment);

            }
        });

    }

    public void changeLang(String lan){
        Locale locale = new Locale(lan);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        requireContext().getResources().updateConfiguration(config, requireContext().getResources().getDisplayMetrics());
    }
}