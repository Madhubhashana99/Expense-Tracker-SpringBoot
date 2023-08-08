package com.expenses.Expense_Tracker.Service;

import com.expenses.Expense_Tracker.Model.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService {
    List<ExpenseCategory> getAllExpenseCategories();

    ExpenseCategory getExpenseCategoryById(Long id);

    ExpenseCategory addExpenseCategory(ExpenseCategory expenseCategory);

    void updateExpenseCategory(Long id, ExpenseCategory updatedCategory);

    void deleteExpenseCategory(Long id);
}
