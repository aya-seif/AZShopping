package com.ayaabdelaziz.azshopping.auth.ui;

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

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.HomeActivity;


public class SucessRegisterFragment extends Fragment {


    Button start_shopping;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sucess_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        start_shopping = view.findViewById(R.id.start_shopping);
        start_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(requireActivity(), HomeActivity.class));

                NavHostFragment.findNavController(SucessRegisterFragment.this).navigate(R.id.action_sucessRegisterFragment_to_loginFragment);

            }
        });
    }
}