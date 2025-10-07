package com.example.cis_3334_project_1_expense_tracker.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cis_3334_project_1_expense_tracker.data.Expense;
import com.example.cis_3334_project_1_expense_tracker.repository.ExpenseRepository;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private final ExpenseRepository repository;
    private final LiveData<List<Expense>> allExpenses;
    private final LiveData<Double> totalSpent;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();
        totalSpent = repository.getTotalSpent();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public LiveData<Double> getTotalSpent() {
        return totalSpent;
    }

    public void insert(Expense expense) {
        repository.insert(expense);
    }
}

