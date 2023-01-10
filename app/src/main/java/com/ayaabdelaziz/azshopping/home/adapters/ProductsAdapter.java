package com.ayaabdelaziz.azshopping.home.adapters;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.pojo.ProductsByCategory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.util.ArrayList;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    Context ctx;
    List<ProductsByCategory> data;
    Fragment fragment;

    public ProductsAdapter(Context ctx, List<ProductsByCategory> data, Fragment fragment) {
        this.ctx = ctx;
        this.data = data;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.product_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.product_name.setText(data.get(position).getTitle());
        holder.product_price.setText("$" + data.get(position).getPrice());
        ArrayList<String> images = data.get(position).images;
        Glide.with(ctx).load("https://picsum.photos/200/300").signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).
                transition(withCrossFade(new DrawableCrossFadeFactory.Builder().
                        setCrossFadeEnabled(true).build())).into(holder.product_image);

//        Glide.with(ctx).load(data.get(position).getImage()).
//                into(holder.category_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", data.get(pos).id);
                NavHostFragment.findNavController(fragment).navigate(R.id.action_productsFragment_to_productDetailFragment, bundle);

            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView product_price;
        public ImageView product_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_iv);

        }
    }
}

