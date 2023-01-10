package com.ayaabdelaziz.azshopping.home.ui;

import static android.os.ParcelFileDescriptor.MODE_APPEND;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.local.CartAdapter;
import com.ayaabdelaziz.azshopping.local.CartDao;
import com.ayaabdelaziz.azshopping.local.CartModel;
import com.ayaabdelaziz.azshopping.local.FavoriteAdapter;
import com.ayaabdelaziz.azshopping.local.FavoriteDao;
import com.ayaabdelaziz.azshopping.local.FavoriteDatabase;
import com.ayaabdelaziz.azshopping.utils.TypeConverter;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {


    CartDao cartDao;
    CartAdapter cartAdapter;
    RecyclerView cart_recyclerView;

    TextView total, shipping, bagtotal;
    ImageView iv_back_cart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cart_recyclerView = view.findViewById(R.id.cart_recyclerView);
        total = view.findViewById(R.id.total);
        iv_back_cart = view.findViewById(R.id.iv_back_cart);
        shipping = view.findViewById(R.id.shipping);
        bagtotal = view.findViewById(R.id.bag_total);
        cartDao = FavoriteDatabase.getDatabase(requireActivity()).getCartDao();
        cartAdapter = new CartAdapter(requireActivity(), cartDao.getAllCartLists(),CartFragment.this);
        cart_recyclerView.setAdapter(cartAdapter);
        iv_back_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CartFragment.this).navigate(R.id.action_cartFragment_to_homeFragment);
            }
        });
        new ItemTouchHelper(cartAdapter.itemTouchHelperCallBack).attachToRecyclerView(cart_recyclerView);
        int tot = 0;
        List<CartModel> arr = cartDao.getAllCartLists();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.size() !=0){
                tot += arr.get(i).getPrice();
                Log.d("TAG", "onViewCreated: " + tot);
            }else if(cartAdapter.getItemCount() ==0) {

                onDetach();
                onAttach(requireContext());
            }

        }
        SharedPreferences sh = requireActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        int a = sh.getInt("num", 0);
        total.setText(String.valueOf(tot) + "$");
        if (tot > 0) {
            bagtotal.setText(getbagTotal(tot) + "$");

        } else {
            bagtotal.setText(String.valueOf(0)+"$");

        }
        if (arr.size()==0){
            zero();
        }
    }

    public int getbagTotal(int num) {
        return Integer.parseInt(total.getText().toString().substring(0, total.getText().toString().length() - 1)) - Integer.parseInt(shipping.getText().toString().substring(0, shipping.getText().toString().length() - 1));
    }

    public void zero(){

        total.setText("0$");
        bagtotal.setText("0$");
    }


}