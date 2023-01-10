package com.ayaabdelaziz.azshopping.home.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.auth.ui.MainActivity;
import com.ayaabdelaziz.azshopping.onboarding.SplashActivity;
import com.ayaabdelaziz.azshopping.utils.SharedPref;


public class ProfileFragment extends Fragment {


    ImageView to_favorite , to_orders,iv_back_profile,to_settings;
    SharedPref sharedPref;
    TextView profile_name , profile_email;
    Button logOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        to_favorite = view.findViewById(R.id.to_favorite);
        to_settings = view.findViewById(R.id.to_settings);
        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);
        to_orders = view.findViewById(R.id.to_cart);
        logOut = view.findViewById(R.id.logout);
        sharedPref = new SharedPref(requireContext());
        iv_back_profile = view.findViewById(R.id.iv_back_profile);
        profile_name.setText(sharedPref.getUserName());
        profile_email.setText(sharedPref.getEmail());
        to_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this).navigate(R.id.action_profileFragment_to_whishListFragment);
            }
        });
        to_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this).navigate(R.id.action_profileFragment_to_cartFragment);
            }
        });

        iv_back_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this).navigate(R.id.action_profileFragment_to_homeFragment);
            }
        });

        to_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProfileFragment.this).navigate(R.id.action_profileFragment_to_languagesFragment);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);

                sharedPref.savePassword(null);
                sharedPref.saveEmail(null);
                sharedPref.saveUserName(null);
            }
        });
    }
}