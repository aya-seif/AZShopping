package com.ayaabdelaziz.azshopping.auth.ui;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.ayaabdelaziz.azshopping.auth.remote.AuthViewModel;
import com.ayaabdelaziz.azshopping.auth.remote.UserResponse;
import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.utils.SharedPref;
import com.google.gson.Gson;


public class SignUpFragment extends Fragment {

    Button btnSignUp;
    AuthViewModel authViewModel;
    UserResponse user;
    EditText emailEd, nameEd, passwordEd,confirm_passwordEd;
    CheckBox checkBox;
    SharedPref sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignUp = view.findViewById(R.id.btn_SignUp);
        emailEd = view.findViewById(R.id.email);
        nameEd = view.findViewById(R.id.nameEd);
        passwordEd = view.findViewById(R.id.password_sign);
        confirm_passwordEd = view.findViewById(R.id.confirm_password);
        checkBox = view.findViewById(R.id.checkbox);
        sharedPref = new SharedPref(requireContext());

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authViewModel = new ViewModelProvider(SignUpFragment.this).get(AuthViewModel.class);
                String email = emailEd.getText().toString();
                String password = passwordEd.getText().toString();
                String name = nameEd.getText().toString();
                String confirm_pass = confirm_passwordEd.getText().toString();
                checkEditText(name,email,password,confirm_pass);
                user = new UserResponse(name, email,password, "https://api.lorem.space/image/watch?w=640&h=480&r=4552", 16, "customer");
                Gson gson = new Gson();
                String requestBody = gson.toJson(user);
                authViewModel.createUser(requestBody);
                if (checkBox.isChecked()){
                    authViewModel.liveData.observe(requireActivity(), new Observer<UserResponse>() {
                        @Override
                        public void onChanged(UserResponse userResponse) {
                            if (userResponse == null) {
                                Log.d("signup", "onChanged: failed");
                                Toast.makeText(requireActivity(), "please try again", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(requireActivity(), "Signed up successfully", Toast.LENGTH_SHORT).show();
                                NavHostFragment.findNavController(SignUpFragment.this).navigate(R.id.sucessRegisterFragment);
                                sharedPref.saveUserName(name);
                                Log.d("signup", "onChanged: succeed");
                            }
                        }
                    });

                }else{
//                    Toast.makeText(requireActivity(), "Complete your Registration", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    void checkEditText(String _name, String _email, String _password,String confirm_pass) {
        if (TextUtils.isEmpty(_name)) {
            nameEd.setError("Name is required");
        }else if (TextUtils.isEmpty(_email)) {
            emailEd.setError("Email is required");
        } else if (TextUtils.isEmpty(_password)) {
            passwordEd.setError("Password is required");
        } else if (TextUtils.isEmpty(confirm_pass)){
            confirm_passwordEd.setError("Field is required");
        } else if (!_password.equals(confirm_pass) ) {
            confirm_passwordEd.setError("Password must be same");
        }
    }
}