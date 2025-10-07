package com.example.cis_3334_project_1_expense_tracker;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cis_3334_project_1_expense_tracker.data.Expense;
import com.example.cis_3334_project_1_expense_tracker.databinding.FragmentAddExpenseBinding;
import com.example.cis_3334_project_1_expense_tracker.viewmodel.ExpenseViewModel;

import java.util.Date;

public class AddExpenseFragment extends Fragment {

    private FragmentAddExpenseBinding binding;
    private ExpenseViewModel expenseViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        setupSpinner();

        binding.buttonSave.setOnClickListener(v -> saveExpense());
    }

    private void setupSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.expense_categories, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        binding.spinnerCategory.setAdapter(adapter);
    }

    private void saveExpense() {
        String name = binding.editTextName.getText().toString();
        String amountStr = binding.editTextAmount.getText().toString();
        String category = binding.spinnerCategory.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(amountStr)) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            Expense expense = new Expense(name, amount, category, new Date());
            expenseViewModel.insert(expense);

            Toast.makeText(getContext(), "Expense saved!", Toast.LENGTH_SHORT).show();

            // Navigate back to the list fragment
            NavHostFragment.findNavController(AddExpenseFragment.this).navigateUp();
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
    