package com.ayaabdelaziz.azshopping.auth.remote;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ayaabdelaziz.azshopping.auth.model.LoginResponse;
import com.ayaabdelaziz.azshopping.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    AuthApi authApi = RetrofitClient.getInstance().getAuthApi();
    MutableLiveData<LoginResponse> mutableLiveData = new MutableLiveData<LoginResponse>();
    public LiveData<LoginResponse> liveData = mutableLiveData;


    public void login(String body) {
        Call<LoginResponse> call = authApi.loginUser(body);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                    Log.d("result", "onResponse: succsess");
                } else {
                    mutableLiveData.postValue(null);
                    Log.d("result", "onResponse: failed" + response.code());

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.d("result", "onResponse: failed" + t.getMessage());


            }
        });
    }

}
