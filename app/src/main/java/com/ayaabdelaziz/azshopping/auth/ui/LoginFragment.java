package com.ayaabdelaziz.azshopping.auth.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.auth.model.LoginResponse;
import com.ayaabdelaziz.azshopping.auth.remote.LoginViewModel;
import com.ayaabdelaziz.azshopping.home.HomeActivity;
import com.ayaabdelaziz.azshopping.utils.SharedPref;
import com.google.gson.Gson;


public class LoginFragment extends Fragment {

    Button btnSignUp, btn_login;
    LoginResponse user;
    LoginViewModel loginViewModel;
    EditText emailEd, password;
    SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.fragment_login,
                        container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        emailEd = view.findViewById(R.id.emailEd);
        password = view.findViewById(R.id.password);
        btn_login = view.findViewById(R.id.btn_login);
        sharedPref = new SharedPref(requireContext());
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel = new ViewModelProvider(LoginFragment.this).get(LoginViewModel.class);
                user = new LoginResponse(emailEd.getText().toString(), password.getText().toString());
                Gson gson = new Gson();
                String requestBody = gson.toJson(user);
                loginViewModel.login(requestBody);
                loginViewModel.liveData.observe(requireActivity(), new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse userResponse) {
                        if (userResponse == null) {
                            Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("TAG", "onChanged:successss ");
                            Toast.makeText(requireActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(requireActivity(), HomeActivity.class));
                            sharedPref.saveEmail(emailEd.getText().toString());
                            sharedPref.savePassword(password.getText().toString());
                        }
                    }
                });
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_signUpFragment);


            }
        });

    }
}