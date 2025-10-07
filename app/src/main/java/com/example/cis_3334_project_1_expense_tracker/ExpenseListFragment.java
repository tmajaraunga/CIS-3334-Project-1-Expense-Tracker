// In ExpenseListFragment.java
package com.example.cis_3334_project_1_expense_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.cis_3334_project_1_expense_tracker.databinding.FragmentExpenseListBinding;
import com.example.cis_3334_project_1_expense_tracker.ui.ExpenseAdapter;
import com.example.cis_3334_project_1_expense_tracker.viewmodel.ExpenseViewModel;
import java.util.Locale;

public class ExpenseListFragment extends Fragment {

    private FragmentExpenseListBinding binding;
    private ExpenseViewModel expenseViewModel;
    private ExpenseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentExpenseListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Initialize ViewModel
        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        // 2. Setup RecyclerView
        setupRecyclerView();

        // 3. Observe LiveData
        observeViewModel();
    }

    private void setupRecyclerView() {
        adapter = new ExpenseAdapter();
        binding.recyclerViewExpenses.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewExpenses.setAdapter(adapter);
    }

    private void observeViewModel() {
        // Observe the list of expenses
        expenseViewModel.getAllExpenses().observe(getViewLifecycleOwner(), expenses -> {
            if (expenses != null) {
                adapter.setExpenses(expenses);
            }
        });

        // Observe the total amount spent
        expenseViewModel.getTotalSpent().observe(getViewLifecycleOwner(), total -> {
            if (total != null) {
                binding.textViewTotalSpent.setText(String.format(Locale.getDefault(), "Total: $%.2f", total));
            } else {
                binding.textViewTotalSpent.setText("Total: $0.00");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
    