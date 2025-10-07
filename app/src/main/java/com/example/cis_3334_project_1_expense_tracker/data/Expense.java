package com.example.cis_3334_project_1_expense_tracker.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "expenses")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public double amount;
    public String category;
    public Date date;

    // You can add constructors, getters, and setters as needed.
    // For Room, a no-argument constructor is often useful,
    // but Room can use the constructor with arguments if the names match.
    public Expense(String name, double amount, String category, Date date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}

