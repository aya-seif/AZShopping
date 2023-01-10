package com.ayaabdelaziz.azshopping.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;


import com.ayaabdelaziz.azshopping.R;
import com.ayaabdelaziz.azshopping.home.adapters.ProductsAdapter;
import com.ayaabdelaziz.azshopping.home.pojo.ProductsByCategory;
import com.ayaabdelaziz.azshopping.home.viewmodels.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    ProductsAdapter productsAdapter;
    RecyclerView recyclerView;
    ImageView iv_back_products;
    SearchView searchView;
    TextView title;
    String search_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.products_recycler);
        iv_back_products = view.findViewById(R.id.iv_back_products);
        searchView = view.findViewById(R.id.searchView_products);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        iv_back_products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ProductsFragment.this).navigate(R.id.action_productsFragment_to_homeFragment);
            }
        });
        title = view.findViewById(R.id.title);
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String name = bundle.getString("title");
        title.setText(name);

        categoryViewModel.getProductsByCategory(id);
        categoryViewModel.productsByCategoryLiveData.observe(requireActivity(), new Observer<List<ProductsByCategory>>() {
            @Override
            public void onChanged(List<ProductsByCategory> productsByCategories) {
                if (productsByCategories != null) {
                    getActivity();
                    productsAdapter = new ProductsAdapter(requireContext(), productsByCategories, ProductsFragment.this);
                    recyclerView.setAdapter(productsAdapter);

                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!query.isEmpty()) {
                    search_item = query;
                    List<ProductsByCategory> arr = search(query.trim());
                    Log.d("word", "onQueryTextSubmit: " + query);
                    ProductsAdapter new_adapter = new ProductsAdapter(requireContext(), arr, ProductsFragment.this);
                    recyclerView.setAdapter(new_adapter);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                search_item = newText;
                List<ProductsByCategory> arr = search(newText.trim());
                Log.d("word", "onQueryTextSubmit: " + newText);
                ProductsAdapter new_adapter = new ProductsAdapter(requireContext(), arr, ProductsFragment.this);
                recyclerView.setAdapter(new_adapter);
                return true;
            }
        });
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.toolbar_menu, menu);
//        final MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//    }

    List<ProductsByCategory> search(String query) {
        List<ProductsByCategory> arr = new ArrayList<>();
        categoryViewModel.productsByCategoryLiveData.observe(requireActivity(), new Observer<List<ProductsByCategory>>() {
            @Override
            public void onChanged(List<ProductsByCategory> productsByCategories) {
                for (ProductsByCategory response : productsByCategories) {
                    if (response.getTitle().toLowerCase().equals(query)) {
                        arr.add(response);
                    }
                }
            }
        });
        return arr;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        searchView.setQueryHint("");
        searchView.clearFocus();

    }
}
