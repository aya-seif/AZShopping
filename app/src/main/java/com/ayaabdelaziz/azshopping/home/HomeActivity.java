package com.ayaabdelaziz.azshopping.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.ayaabdelaziz.azshopping.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_nav);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment2);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.productDetailFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (navDestination.getId() == R.id.cartFragment) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else {
                    bottomNavigationView.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}