package com.ayaabdelaziz.azshopping.retrofit;

import com.ayaabdelaziz.azshopping.home.remote.API;
import com.ayaabdelaziz.azshopping.auth.remote.AuthApi;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;

    private API myApi;
    private AuthApi authApi;

    private RetrofitClient() {
        GsonBuilder gsonBuilder = new GsonBuilder().serializeNulls();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        myApi = retrofit.create(API.class);
        authApi = retrofit.create(AuthApi.class);

    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public API getMyApi() {
        return myApi;
    }

    public AuthApi getAuthApi() {
        return authApi;
    }


}
