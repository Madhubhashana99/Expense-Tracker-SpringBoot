package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.Expense;
import com.expenses.Expense_Tracker.Model.ExpenseCategory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ExpenseService {

    Expense addExpense(Expense expense);

    Expense updateExpense(Long id, Expense updatedExpense);

    void deleteExpense(Long id);

    Expense getExpenseById(Long id);

    List<Expense> getAllExpenses();

    BigDecimal getTotalExpenseAmount();

    Map<ExpenseCategory, BigDecimal> getExpenseSummariesByCategory();

    Map<String, BigDecimal> getExpenseSummariesByMonth();

    Map<Integer, BigDecimal> getExpenseSummariesByYear();



}
