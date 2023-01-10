package com.ayaabdelaziz.azshopping.local;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.utils.TypeConverter;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {


    Context ctx;
    List<CartModel> data;
    Fragment fragment;

    public CartAdapter(Context ctx, List<CartModel> data, Fragment fragment) {
        this.ctx = ctx;
        this.data = data;
        this.fragment = fragment;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.cart_item, parent, false);
        CartAdapter.MyViewHolder viewHolder = new CartAdapter.MyViewHolder(recyclerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        int pos = position;
        holder.cart_name.setText(data.get(position).getName());
        holder.cart_price.setText("$" + data.get(position).getPrice());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.number.setText(TypeConverter.convertToString(TypeConverter.convertToIntAdd(holder.number.getText().toString())));
            }
        });
        holder.subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(holder.number.getText().toString()) >= 1) {
                    holder.number.setText(TypeConverter.convertToString(TypeConverter.convertToIntminus(holder.number.getText().toString())));

                }
            }
        });

        try {
            byte[] array = data.get(position).getImages();
            holder.cart_iv.setImageBitmap(BitmapFactory.decodeByteArray(array, 0, array.length));
        } catch (Exception e) {

        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cart_name, number;
        public TextView cart_price;
        public ImageView cart_iv, add, subtraction;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_iv = itemView.findViewById(R.id.cart_iv);
            add = itemView.findViewById(R.id.add);
            number = itemView.findViewById(R.id.number);
            subtraction = itemView.findViewById(R.id.subtraction);

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
            CartDao dao = FavoriteDatabase.getDatabase(ctx).getCartDao();
            dao.deleteCart(data.get(viewHolder.getAdapterPosition()));
            Toast.makeText(ctx, "Removed", Toast.LENGTH_SHORT).show();
            onItemDismiss(viewHolder.getAdapterPosition());


        }
    };


}




