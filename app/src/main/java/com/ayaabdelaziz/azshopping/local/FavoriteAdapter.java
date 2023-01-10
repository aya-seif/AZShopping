package com.ayaabdelaziz.azshopping.local;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.adapters.ProductsAdapter;
import com.ayaabdelaziz.azshopping.home.pojo.ProductsByCategory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {


    Context ctx;
    List<FavoriteModel> data;

    public FavoriteAdapter(Context ctx, List<FavoriteModel> data) {
        this.ctx = ctx;
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.wishlist_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.product_name.setText(data.get(position).getName());
        holder.product_price.setText("$" + data.get(position).getPrice());
        try {
            byte[] array = data.get(position).getImages();
            holder.product_image.setImageBitmap(BitmapFactory.decodeByteArray(array, 0, array.length));
        } catch (Exception e) {

        }

        try {
            holder.add_to_cart.setOnClickListener(new View.OnClickListener() {
                CartModel model = new CartModel();

                @Override
                public void onClick(View v) {
                    CartDao dao = FavoriteDatabase.getDatabase(ctx).getCartDao();
                    model.setName(data.get(pos).name);
                    model.setPrice(data.get(pos).price);
                    model.setImages(data.get(pos).getImages());
                    dao.insertCart(model);
                    Toast.makeText(ctx, "Added to cart successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView product_name;
        public TextView product_price;
        public ImageView product_image;
        Button add_to_cart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_name = itemView.findViewById(R.id.productItemName);
            product_price = itemView.findViewById(R.id.productItemPrice);
            product_image = itemView.findViewById(R.id.productItemIv);
            add_to_cart = itemView.findViewById(R.id.add_to_cart);

        }
    }

    public void onItemDismiss(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            FavoriteDao dao = FavoriteDatabase.getDatabase(ctx).getFavoriteDao();
            dao.deleteWishlist(data.get(viewHolder.getAdapterPosition()));
            Toast.makeText(ctx, "Removed", Toast.LENGTH_SHORT).show();
            onItemDismiss(viewHolder.getAdapterPosition());


        }
    };


}


