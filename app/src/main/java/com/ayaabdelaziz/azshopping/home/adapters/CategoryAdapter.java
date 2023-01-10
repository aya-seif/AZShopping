package com.ayaabdelaziz.azshopping.home.adapters;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.ayaabdelaziz.azshopping.home.pojo.CategoryResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    Context ctx;
    List<CategoryResponse> data;
    Fragment fragment;

    public CategoryAdapter(Context ctx, List<CategoryResponse> data, Fragment fragment) {
        this.fragment = fragment;
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.category_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.category_name.setText(data.get(position).getName());
        Log.d("image", "onBindViewHolder: " + data.get(position).getName());
//        Glide.with(ctx).load(data.get(position).getImage()).
//                transition(withCrossFade(new DrawableCrossFadeFactory.Builder().
//                        setCrossFadeEnabled(true).build())).into(holder.category_image);


        Glide.with(ctx).load("https://picsum.photos/200/300").signature(new ObjectKey(String.valueOf(System.currentTimeMillis()))).

                into(holder.category_image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", data.get(pos).getId());
                bundle.putString("title",data.get(pos).getName());
                NavHostFragment.findNavController(fragment).navigate(R.id.action_homeFragment_to_productsFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category_name;
        public ImageView category_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category_name = itemView.findViewById(R.id.category_name);
            category_image = itemView.findViewById(R.id.category_image);

        }
    }
}

