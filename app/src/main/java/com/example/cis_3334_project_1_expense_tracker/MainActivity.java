package com.example.cis_3334_project_1_expense_tracker;

import android.os.Bundle;
// ... other imports
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

// ADD THIS IMPORT
import androidx.navigation.fragment.NavHostFragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cis_3334_project_1_expense_tracker.databinding.ActivityMainBinding;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // --- START: CORRECTED CODE ---

        // 1. Get the NavHostFragment using the FragmentManager
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);

        // 2. Get the NavController from the NavHostFragment
        NavController navController = navHostFragment.getNavController();

        // --- END: CORRECTED CODE ---


        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_ExpenseListFragment_to_AddExpenseFragment);
            }
        });
    }

    // ... rest of your MainActivity is correct ...

    @Override
    public boolean onSupportNavigateUp() {
        // You should also update this part to be consistent
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();

        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
