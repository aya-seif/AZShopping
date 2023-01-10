package com.ayaabdelaziz.azshopping.auth.remote;

import android.util.Log;

import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ayaabdelaziz.azshopping.auth.model.LoginResponse;
import com.ayaabdelaziz.azshopping.auth.model.User;
import com.ayaabdelaziz.azshopping.retrofit.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    MutableLiveData<UserResponse> mutableLiveData = new MutableLiveData<>();
    AuthApi authApi = RetrofitClient.getInstance().getAuthApi();
    public LiveData<UserResponse> liveData = mutableLiveData;

    public void createUser(String  body) {

        Call<UserResponse> call = authApi.createUser(body);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Log.d("signup", "onResponse:failed " + response.code());
                    mutableLiveData.postValue(null);
                } else {
                    mutableLiveData.postValue(response.body());
                    Log.d("signup", "onResponse:sb77777 ");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("signup", "onResponse:failed " + t.getMessage().toString());
                mutableLiveData.postValue(null);

            }
        });

    }
}
