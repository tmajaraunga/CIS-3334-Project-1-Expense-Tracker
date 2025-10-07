package com.example.cis_3334_project_1_expense_tracker.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import com.example.cis_3334_project_1_expense_tracker.data.Expense;
import com.example.cis_3334_project_1_expense_tracker.data.ExpenseDao;
import com.example.cis_3334_project_1_expense_tracker.data.ExpenseDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExpenseRepository {

    private final ExpenseDao expenseDao;
    private final LiveData<List<Expense>> allExpenses;
    private final LiveData<Double> totalSpent;

    // ExecutorService for running database operations on a background thread
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor();

    public ExpenseRepository(Application application) {
        ExpenseDatabase db = ExpenseDatabase.getDatabase(application);
        expenseDao = db.expenseDao();
        allExpenses = expenseDao.getAllExpenses();
        totalSpent = expenseDao.getTotalSpent();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public LiveData<Double> getTotalSpent() {
        return totalSpent;
    }

    public void insert(Expense expense) {
        // Run the insert operation on a background thread
        databaseExecutor.execute(() -> {
            expenseDao.insert(expense);
        });
    }
}

