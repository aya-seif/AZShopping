package com.ayaabdelaziz.azshopping.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.local.FavoriteAdapter;
import com.ayaabdelaziz.azshopping.local.FavoriteDao;
import com.ayaabdelaziz.azshopping.local.FavoriteDatabase;
import com.ayaabdelaziz.azshopping.local.FavoriteModel;

import java.util.ArrayList;
import java.util.List;


public class WishListFragment extends Fragment {


    FavoriteDao favoriteDao;
    FavoriteAdapter favoriteAdapter;
    RecyclerView fav_recyclerView;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wish_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fav_recyclerView = view.findViewById(R.id.fav_recyclerView);
        searchView = view.findViewById(R.id.searchView_wishList);
        favoriteDao = FavoriteDatabase.getDatabase(requireActivity()).getFavoriteDao();
        List<FavoriteModel> model = favoriteDao.getAllWishlistItems();
        favoriteAdapter = new FavoriteAdapter(requireActivity(),model);
        fav_recyclerView.setAdapter(favoriteAdapter);
        new ItemTouchHelper(favoriteAdapter.itemTouchHelperCallBack).attachToRecyclerView(fav_recyclerView);
        favoriteAdapter.notifyDataSetChanged();
        fav_recyclerView.setHasFixedSize(true);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<FavoriteModel>list = new ArrayList<>();
                FavoriteDao favoriteDaao = FavoriteDatabase.getDatabase(requireActivity()).getFavoriteDao();
                List<FavoriteModel> favo = favoriteDaao.getAllWishlistItems();
                for (FavoriteModel fav : favo){
                    if (fav.getName().toLowerCase().equals(newText)){
                        list.add(fav);
                    }
                }
                FavoriteAdapter adapter = new FavoriteAdapter(requireActivity(),list);
                fav_recyclerView.setAdapter(adapter);

                return true;
            }
        });

    }
}