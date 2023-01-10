package com.ayaabdelaziz.azshopping.home.remote;

import com.ayaabdelaziz.azshopping.home.pojo.CategoryResponse;
import com.ayaabdelaziz.azshopping.home.pojo.ProductResponse;
import com.ayaabdelaziz.azshopping.home.pojo.ProductsByCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    public static final String BASE_URL = "https://api.escuelajs.co/api/v1/";

    @GET("categories")
    Call<List<CategoryResponse>> getAllCategories();

    @GET("categories?name=")
    Call<List<CategoryResponse>> getSearchResultInCategory();


    @GET("categories/{categoryID}/products")
    Call<List<ProductsByCategory>> getProductsByCategory(@Path("categoryID") int id);

    @GET("products/{id}")
    Call<ProductResponse> getProductById(@Path("id") int id);

}
