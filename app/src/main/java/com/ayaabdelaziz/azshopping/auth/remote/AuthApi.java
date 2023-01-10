package com.ayaabdelaziz.azshopping.auth.remote;

import com.ayaabdelaziz.azshopping.auth.model.LoginResponse;
import com.ayaabdelaziz.azshopping.auth.model.User;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {


    @POST("users/")
    @Headers("Content-Type: application/json")
    Call<UserResponse> createUser(@Body String body);


    @POST("auth/login")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> loginUser(@Body String body);

}
