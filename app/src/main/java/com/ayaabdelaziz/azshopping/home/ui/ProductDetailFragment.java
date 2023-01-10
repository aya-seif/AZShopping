package com.ayaabdelaziz.azshopping.home.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.pojo.ProductResponse;
import com.ayaabdelaziz.azshopping.home.viewmodels.CategoryViewModel;
import com.ayaabdelaziz.azshopping.local.CartDao;
import com.ayaabdelaziz.azshopping.local.CartModel;
import com.ayaabdelaziz.azshopping.local.FavoriteDao;
import com.ayaabdelaziz.azshopping.local.FavoriteDatabase;
import com.ayaabdelaziz.azshopping.local.FavoriteModel;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;

public class ProductDetailFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    TextView price, desc, name;
    ImageView addToFavorite,iv_back_productDetail;
    FavoriteModel model;
    CartModel cartModel;
    ImageView productIV;
    Button addToCart;

    FavoriteDao favoriteDao;
    CartDao cartDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productIV = view.findViewById(R.id.productIV);
        Glide.with(requireActivity()).load("https://picsum.photos/200/300").into(productIV);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        price = view.findViewById(R.id.price);
        desc = view.findViewById(R.id.description);
        name = view.findViewById(R.id.productName);
        addToCart = view.findViewById(R.id.addToCart);
        addToFavorite = view.findViewById(R.id.addToFavorite);
        iv_back_productDetail = view.findViewById(R.id.iv_back_productDetail);
        model = new FavoriteModel();
        cartModel = new CartModel();

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        categoryViewModel.getProductById(id);
        iv_back_productDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProductDetailFragment.this).navigate(R.id.action_productDetailFragment_to_homeFragment);
            }
        });
        categoryViewModel.productResponseLiveData.observe(requireActivity(), new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {
                price.setText(String.valueOf(productResponse.price) + "$");
                name.setText(productResponse.title);
                desc.setText(productResponse.description);
                model.setName(productResponse.title);
                cartModel.setName(productResponse.title);
                model.setPrice(productResponse.price);
                cartModel.setPrice(productResponse.price);

                try {
                    Bitmap image=((BitmapDrawable)productIV.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    model.setImages(imageBytes);
                    cartModel.setImages(imageBytes);
                }catch (Exception e){

                }


            }
        });
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteDao = FavoriteDatabase.getDatabase(requireActivity()).getFavoriteDao();
                if (model != null) {
                    favoriteDao.insertWishlist(model);
                    Toast.makeText(requireActivity(), "Saved Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDao = FavoriteDatabase.getDatabase(requireActivity()).getCartDao();
                cartDao.insertCart(cartModel);
                Toast.makeText(requireActivity(), "Added To Cart Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }

}