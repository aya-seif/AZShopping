package com.ayaabdelaziz.azshopping.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;

import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.adapters.CategoryAdapter;
import com.ayaabdelaziz.azshopping.home.pojo.CategoryResponse;
import com.ayaabdelaziz.azshopping.home.viewmodels.CategoryViewModel;
import com.ayaabdelaziz.azshopping.onboarding.SecondScreen;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    CategoryViewModel categoryViewModel;
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;
    ImageView iv_back_home;
    SearchView searchView_home;
    String search_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.category_recyclerView);
        iv_back_home = view.findViewById(R.id.iv_back_home);
        searchView_home = view.findViewById(R.id.searchView_home);

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getCategories(requireContext()).observe(requireActivity(),categoryResponses -> {
            categoryAdapter = new CategoryAdapter(requireContext(),categoryResponses,HomeFragment.this);
            recyclerView.setAdapter(categoryAdapter);

        });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_item =query;
                ArrayList<CategoryResponse> arr = search(query.trim());
                CategoryAdapter adapter = new CategoryAdapter(requireContext(),arr,HomeFragment.this);
                recyclerView.setAdapter(adapter);

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        iv_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }


    ArrayList<CategoryResponse> search (String query){
        ArrayList<CategoryResponse>arr = new ArrayList<>();
        categoryViewModel.getCategories(requireContext()).observe(requireActivity(),categoryResponses -> {
            for(CategoryResponse response : categoryResponses){
                if (response.getName().toLowerCase().equals(query)){
                    arr.add(response);
                }
            }
        });
        return arr;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchView_home.setQueryHint("");
        searchView_home.clearFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        searchView_home.setQueryHint("");
        searchView_home.clearFocus();
    }
}