package com.ayaabdelaziz.azshopping.home.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ayaabdelaziz.azshopping.home.pojo.CategoryResponse;
import com.ayaabdelaziz.azshopping.home.pojo.ProductResponse;
import com.ayaabdelaziz.azshopping.home.pojo.ProductsByCategory;
import com.ayaabdelaziz.azshopping.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryViewModel extends ViewModel {


    private MutableLiveData<List<CategoryResponse>> categories;
    private MutableLiveData<List<ProductsByCategory>> productsByCategory = new MutableLiveData<>();
    public LiveData<List<ProductsByCategory>> productsByCategoryLiveData = productsByCategory;

    private MutableLiveData<ProductResponse> productResponse = new MutableLiveData<>();
    public LiveData<ProductResponse>productResponseLiveData = productResponse;

    public LiveData<List<CategoryResponse>> getCategories(Context ctx) {

        if (categories == null) {
            categories = new MutableLiveData<>();
            loadCategories(ctx);
        }
        return categories;

    }

    private void loadCategories(Context ctx) {

        Call<List<CategoryResponse>> call = RetrofitClient.getInstance().getMyApi().getAllCategories();
        call.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ctx, "" + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    categories.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Toast.makeText(ctx, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


     public void getProductsByCategory(int id) {
        Call<List<ProductsByCategory>> productsCall = RetrofitClient.getInstance().getMyApi().getProductsByCategory(id);
        productsCall.enqueue(new Callback<List<ProductsByCategory>>() {
            @Override
            public void onResponse(Call<List<ProductsByCategory>> call, Response<List<ProductsByCategory>> response) {
                if (!response.isSuccessful()) {
                    Log.d("TAG", "onResponse: Categry Failed " + response.code());
                    productsByCategory.postValue(null);
                } else {
                    productsByCategory.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ProductsByCategory>> call, Throwable t) {
                Log.d("TAG", "onResponse:Failed " + t.getMessage());


            }
        });

    }

    public void getProductById(int id){
        Call<ProductResponse> call = RetrofitClient.getInstance().getMyApi().getProductById(id);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (!response.isSuccessful()){
                    Log.d("product", "onResponse: "+response.code());
                }else {
                    productResponse.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("product", "onResponse: "+t.getMessage());


            }
        });
    }


}
