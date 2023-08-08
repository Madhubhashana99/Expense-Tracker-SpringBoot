package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.Expense;

import java.util.List;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    Expense updateExpense(Long id, Expense updatedExpense);

    void deleteExpense(Long id);

    Expense getExpenseById(Long id);

    List<Expense> getAllExpenses();

}
